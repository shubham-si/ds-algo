package heaps;
/*
    [3,4,1,2,5,8,6,0,......]

Note * (len(max-heap) == len(min-heap)) || (len(max-heap) - len(min-heap) == 1)

<max-heap>							<min-heap>           								   			ans

[3]															     len(max) + 1 > len(min)				3

[3,4]<->[4,3]   	--> 4			 [4]								len(max) == len(min) 			 (3 + 4) / 2 = 7.5

[3,1]									4]								len(max) + 1 > len(min)           3

[3,1,2] <-> [3,2,1] -> 3     			[3,4]							len(max) == len(min)              (3 + 4) / 2 = 7.5

[2,1,5] <-> [5,2,1]
				 --> 5        		[3,4,5]
				 		3 <-
			[3,2,1]            		[4,5]							len(max) + 1 > len(min)                3

[8,3,2,1] ->8  						[4,5,8]							len(max) == len(min) 			 (3 + 4) / 2 = 7.5

[6,3,2,1] ->6						[4,5,6,8]
								4 <--

	[4,3,2,1]						[5,6,8]						  len(max) + 1 > len(min)                4

[4,3,2,1,0]		-> 4 				[4,5,6,8]
	[3,2,1,0]						[4,5,6,8]							len(max) == len(min) 			 (3 + 4) / 2 = 7.5


algo:
	i)    put num to max heap
	ii)   pick peek element from max-heap and put to min-heap
	iii)  if len(min) > len(max)
				pick peek element from min-heap and put to max-heap

		  if len(min) == len(max)
		  	return (max.peek() + min.peek()) / 2
		  else
		  	return max.peek()

 */
public class RunningMedian {

}
