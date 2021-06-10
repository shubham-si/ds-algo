package threading.probsol;

// State.ODD.getState()
// State.values()
enum PrinterType {
    ODD(0),
    EVEN(1);

    private int printerType;
    private PrinterType(int printerType) {
        this.printerType = printerType;
    }

    public int getPrinterType() {
        return this.printerType;
    }
}

class SharedState {
    private PrinterType toPrint;

    public void setToPrint(PrinterType printerType) {
        this.toPrint = printerType;
    }
    public PrinterType toPrint() {
        return this.toPrint;
    }
}

class Printer implements Runnable{
    private SharedState sharedState;
    private PrinterType nextPrinterType;
    private PrinterType currPrinterType;
    int start, increment;

    public Printer(int start, int increment, SharedState sharedState, PrinterType currPrinterType, PrinterType nextPrinterType) {
        this.sharedState = sharedState;
        this.currPrinterType = currPrinterType;
        this.nextPrinterType = nextPrinterType;
        this.start = start;
        this.increment = increment;
    }

    @Override
    public void run() {
        while(this.start <= 50) {
            synchronized (sharedState) {
                while(this.currPrinterType != sharedState.toPrint()) {
                    try {
                        this.sharedState.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.print(this.start + " ");
                this.start += this.increment;
                sharedState.setToPrint(this.nextPrinterType);
                sharedState.notifyAll();
            }
        }
    }

}

public class EvenOddImpl {
    public static void main(String ...args) {
        SharedState sharedState = new SharedState();
        sharedState.setToPrint(PrinterType.EVEN);
        Printer oddPrinter = new Printer(1,2,sharedState, PrinterType.ODD, PrinterType.EVEN);
        Printer evenPrinter = new Printer(0,2,sharedState, PrinterType.EVEN, PrinterType.ODD);
        Thread oddThread = new Thread(oddPrinter);
        Thread evenThread = new Thread(evenPrinter);
        oddThread.start();
        evenThread.start();
    }
}
