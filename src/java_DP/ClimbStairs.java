package java_DP;

/*
 * 方法一：用排列组合解决爬楼梯问题
 * 		遇到的问题，由于使用排列组合运算C时数值过大，超过int范围，所以在n值为25以上出现错误答案，最后使用long解决问题
 * 方法二：用DP算法解决，第n个楼梯的方式等于（n-1）楼梯的方式加上(n-2)楼梯的方式，也就是Fibonacci
 */
public class ClimbStairs {

	public static void main(String[] args) {
		System.out.println(climbStairs(25));
		System.out.println(climbStairs1(25));
	}
	
	public static long climbStairs(int n) {
        long sum=0;
        int count=n/2;
        for(int i=0;i<=count;i++){
            long temp1=1;
            long temp2=1;
            for(int j=1;j<=i;j++){
                temp1=temp1*(n-i+1-j);
                temp2=temp2*j;
            }
            sum=sum+temp1/temp2;
        }
        return sum;
    }
	
	public static int climbStairs1(int n) {
        if(n==1){
            return 1;
        }
        int oneStep=1;
        int twoStep=1;
        int result=0;
        for(int i=2;i<=n;i++){
            result=oneStep+twoStep;
            oneStep=twoStep;
            twoStep=result;
        }
        return result;
    }
}
