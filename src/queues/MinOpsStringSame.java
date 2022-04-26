package queues;

// Minimum Operations To Make Strings Same
/*

Given two strings ‘initial’ and ‘final’ , where ‘initial’ is the initial string and ‘final’ is the final string.
Each state will contain ‘a’,’b’ and only one empty slot represented by ‘_’.
Your task is to transform the initial string into the final string with the minimum number of operation.
Allowed operations are:
1. You can swap empty character with any adjacent character(i-1 or i+1) --> (For example, ‘aba_ab’ can be converted into ‘ab_aab’ or ‘abaa_b’).
2. You can swap empty character with next to the adjacent character only if the adjacent character is different from next to the adjacent character.
   (For example, ‘aba_ab’ can be converted into ‘a_abab’ or ‘ababa_’, but ‘ab_aab’ cannot be converted to ‘abaa_b’ because ‘a’ cannot jump over ‘a’).

    (i-1 & i-2 || i+1 & i+2) are different
 */
public class MinOpsStringSame {

}
