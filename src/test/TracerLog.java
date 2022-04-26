package test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class TracerLog {
    private List<Span> tasks;
    private List<Span> rootList;
    public Long totalCPUTime;
    public Long totalBlockingTime;
    private String traceId;
    private Integer orderNo;
    private Integer spanCount;

    private String[] names;
    private String[] timings;
    private String[] attributes;

    private static final String SEPARATOR = "_";

    public TracerLog() {
        traceId = MiscUtils.generate32BitUUID();
        tasks = new ArrayList<>();
        rootList = new ArrayList<>();
        totalCPUTime = 0L;
        totalBlockingTime = 0L;
        orderNo = 0;
    }

    public static void main(String ...args) {
        String spanNames = "000s_001s_001e_002s_002e_003s_004s_005s_005e_004e_003e_000e_011s_071s_071e_011e_050s_073s_073e_075s_075e_075s_075e_075s_075e_050e_050s_073s_073e_073s_073e_073s_073e_073s_073e_073s_073e_073s_073e_073s_073e_073s_073e_073s_073e_050e_050s_075s_075e_075s_075e_075s_075e_075s_075e_050e_050s_075s_075e_075s_075e_075s_075e_075s_075e_075s_075e_075s_075e_075s_075e_075s_075e_075s_075e_050e_050s_050e_050s_050e_050s_050e_050s_075s_075e_075s_075e_075s_075e_075s_075e_076s_076e_050e";
        String spanTimings = "7626.935_7626.965_7710.695_7710.695_7841.465_7841.470_7842.870_7843.050_7867.490_7867.700_7867.705_7867.710_17433.020_17454.385_17455.790_17496.600_18360.410_18365.460_18365.800_18367.415_18367.560_18368.210_18368.230_18368.690_18368.730_18369.940_18604.900_18613.180_18613.250_18616.085_18616.175_18619.555_18619.595_18621.720_18621.770_18627.835_18627.890_18633.165_18633.190_18635.565_18635.590_18638.090_18638.135_18640.100_18640.145_18641.235_18972.465_18974.900_18974.920_18975.360_18975.380_18975.915_18975.935_18976.350_18976.360_18976.995_18979.285_18980.560_18980.580_18980.985_18980.995_18981.420_18981.430_18981.860_18981.870_18982.555_18982.570_18983.080_18983.105_18983.520_18983.530_18983.925_18983.940_18985.505_18985.530_18986.565_18989.125_18989.715_18990.600_18991.160_18991.850_18992.365_18993.045_18995.050_18995.085_18995.710_18995.725_18996.675_18996.690_18998.460_18998.480_19006.415_19006.475_19007.545";
        String spanAttributes = "________________{\"bidder\":\"medianet\"}_________{\"bidder\":\"medianet\"}_{\"bidder\":\"openx\"}___________________{\"bidder\":\"openx\"}_{\"bidder\":\"conversant\"}_________{\"bidder\":\"conversant\"}_{\"bidder\":\"sovrn\"}___________________{\"bidder\":\"sovrn\"}_{\"bidder\":\"rubicon\"}_{\"bidder\":\"rubicon\"}_{\"bidder\":\"rubicon\"}_{\"bidder\":\"rubicon\"}_{\"bidder\":\"rubicon\"}_{\"bidder\":\"rubicon\"}_{\"bidder\":\"rubicon\"}___________{\"bidder\":\"rubicon\"}";
        TracerLog tracer = new TracerLog();
        try {
            tracer.process(spanNames, spanTimings, spanAttributes);
            System.out.println(tracer.rootList);
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }

    }

    public void process(String spanNames, String spanTimings, String spanAttributes) throws Exception {
        names = spanNames.split(TracerLog.SEPARATOR);
        timings = spanTimings.split(TracerLog.SEPARATOR);
        attributes = spanAttributes.split(TracerLog.SEPARATOR);
        if (!isValidCollection()) {
            throw new Exception(Event.TRACER_NUMBERS_MISMATCH.name());
        }
        tree(0, null);
        computeAdditionalFields();
    }

    private boolean isValidCollection() {
        int spansLength = names.length;
        int timingsLength = timings.length;
        int attributesLength = attributes.length;
        boolean isLengthValid = spansLength % 2 == 0;
        return isLengthValid && spansLength == attributesLength && attributesLength == timingsLength;
    }

    private void tree(int index, Span lastOpenedSpan) throws Exception {
        if (index >= names.length) {
            return;
        }
        String current = names[index];
        if (MiscUtils.isNullOrEmpty(current)) {
            throw new Exception(Event.TRACER_INVALID_SPAN_NAME.name());
        }
        if (isClosingSpan(current) && IsInvalidSpan(lastOpenedSpan, current)) {
            throw new Exception(Event.TRACER_ORDER_MISMATCH.name());
        }
        if (isClosingSpan(current)) {
            updateSpan(lastOpenedSpan, timings[index]);
            tree(index + 1, lastOpenedSpan.getParent());
        } else {
            Span span = new Span(current, timings[index], attributes[index], lastOpenedSpan);
            span.setTaskOrder(++orderNo);
            this.tasks.add(span);
            if (lastOpenedSpan == null) {
                rootList.add(span);
            } else {
                lastOpenedSpan.getChild().add(span);
            }
            tree(index + 1, span);
        }
    }

    private boolean isClosingSpan(String taskName) {
        return taskName.endsWith(TraceEventConstants.END.getValue());
    }

    private boolean IsInvalidSpan(Span lastOpenedSpan, String taskName) {
        // spans = 002s_003s_002e_003e
        // eg: task = 003, taskName = 002
        String taskNumber = taskName.split(TraceEventConstants.END.getValue())[0];
        return !(lastOpenedSpan != null && lastOpenedSpan.getTaskNo().equals(taskNumber));
    }

    private void updateSpan(Span task, String time) {
        task.setEndTime(Double.valueOf(MiscUtils.getDouble(time) * 1000).longValue());
        task.setCpuTime(task.getEndTime() - task.getStartTime());
        task.setBlockingTime(Math.max(task.getCpuTime() - 50, 0));
    }

    private void computeAdditionalFields() {
        for (Span task : this.rootList) {
            totalCPUTime += task.getCpuTime();
            totalBlockingTime += task.getBlockingTime();
        }
        spanCount = names.length / 2;
    }
}

class Span {
    private String spanId;
    private String taskNo;
    private Long startTime;
    private Long endTime;
    private List<Span> child;
    private Span parent;

    public String getSpanId() {
        return spanId;
    }

    public void setSpanId(String spanId) {
        this.spanId = spanId;
    }

    public String getTaskNo() {
        return taskNo;
    }

    public void setTaskNo(String taskNo) {
        this.taskNo = taskNo;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public List<Span> getChild() {
        return child;
    }

    public void setChild(List<Span> child) {
        this.child = child;
    }

    public Span getParent() {
        return parent;
    }

    public void setParent(Span parent) {
        this.parent = parent;
    }

    public Long getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(Long cpuTime) {
        this.cpuTime = cpuTime;
    }

    public Long getBlockingTime() {
        return blockingTime;
    }

    public void setBlockingTime(Long blockingTime) {
        this.blockingTime = blockingTime;
    }

    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    public Integer getTaskOrder() {
        return taskOrder;
    }

    public void setTaskOrder(Integer taskOrder) {
        this.taskOrder = taskOrder;
    }

    private Long cpuTime;
    private Long blockingTime;
    private String attribute;
    private Integer taskOrder;

    Span(String taskName, String time, String attribute, Span parent) throws Exception {
        taskNo = taskName.split(TraceEventConstants.START.getValue())[0];
        if (taskNo == null) {
            throw new Exception(Event.TRACER_INCORRECT_START_SPAN.name());
        }
        spanId = MiscUtils.generate16BitUUID();
        startTime = Double.valueOf(MiscUtils.getDouble(time) * 1000).longValue();
        child = new ArrayList<>();
        this.parent = parent;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return "(" + this.taskNo + " " + this.child.toString() + ")";
    }
}

enum TraceEventConstants {
    START("s"),
    END("e");

    private final String value;

    TraceEventConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

enum Event {
    TRACER_NUMBERS_MISMATCH("Tracer error length mismatch"),
    TRACER_INCORRECT_START_SPAN("Tracer error incorrect start span"),
    TRACER_INVALID_SPAN_NAME("Tracer error invalid span name"),
    TRACER_ORDER_MISMATCH("Tracer error order mismatch");

    private final String value;

    Event(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

class MiscUtils {

    private MiscUtils() {
    }

    public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat simpleDateFormatMs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    public static final String UTF8 = "UTF-8";

    public static Integer getRandomInt(Integer n) {
        return ThreadLocalRandom.current().nextInt(n);
    }

    public static boolean isNotNullOrEmpty(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isStringSet(String str) {
        return !isNullOrEmpty(str);
    }

    public static boolean isNotNullOrEmpty(byte[] ar) {
        return (ar != null  && ar.length > 0);
    }

    public static boolean isNullOrEmpty(String str) {
        return (null == str || str.isEmpty());
    }

    public static boolean isNotNullInt(Integer integer) {
        return !isNullInt(integer);
    }

    public static boolean isNullInt(Integer integer) {
        return (null == integer);
    }

    public static boolean isNull(Object obj) {
        return obj == null;
    }

    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    public static String getStackTrace(Exception e) {
        String st;
        StringWriter stackTrace = new StringWriter();
        e.printStackTrace(new PrintWriter(stackTrace));
        st = stackTrace.toString();
        return st.substring(0, Math.min(st.length(), 900));
    }

    public static long getCurrentSysTimeMs() {
        return System.currentTimeMillis();
    }

    public static String getCurrentTimestamp() {
        return simpleDateFormat.format(new Date());
    }

    public static String getCurrentTimestamp(long ts) {
        return simpleDateFormatMs.format(ts);
    }

    public static Double getDouble(String s) {
        if(s!= null && !s.trim().isEmpty()){
            return Double.valueOf(s);
        }
        return null;
    }

    public static Integer getInteger(String s) {
        if(s!= null && !s.trim().isEmpty()){
            return Integer.valueOf(s);
        }
        return null;
    }

    public static Long getLong(String s) {
        if(s!= null && !s.trim().isEmpty()){
            return Long.valueOf(s);
        }
        return null;
    }

    public static String generate32BitUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String generate16BitUUID() {
        return generate32BitUUID().substring(0, 16);
    }

}