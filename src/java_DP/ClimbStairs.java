package java_DP;

/*
 * ����һ����������Ͻ����¥������
 * 		���������⣬����ʹ�������������Cʱ��ֵ���󣬳���int��Χ��������nֵΪ25���ϳ��ִ���𰸣����ʹ��long�������
 * ����������DP�㷨�������n��¥�ݵķ�ʽ���ڣ�n-1��¥�ݵķ�ʽ����(n-2)¥�ݵķ�ʽ��Ҳ����Fibonacci
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
