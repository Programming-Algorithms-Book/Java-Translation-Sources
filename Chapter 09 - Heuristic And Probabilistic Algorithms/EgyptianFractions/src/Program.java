public class Program {
    public static void main(String[] args)
    {
        solve(3, 7);
    }

    /* намалява p/q, докато p стане равно на 1 */
    private static Result cancel(long p, long q)
    {
        if (q % p == 0)
        {
            q /= p;
            p = 1;
        }
        
        return new Result(p, q);
    }

    private static void solve(long p, long q)
    {
        System.out.printf("%d/%d = ", p, q);
        Result result = cancel(p, q);
        p = result.getFirstValue();
        q = result.getSecondValue();

        while (p > 1)
        {
            /* намира максималната дроб 1/r, 1/r<=p/q */
            long r = (q + p) / p;
            System.out.printf("%d/%d + ", 1, r);

            /* изчислява p/q - 1/r */
            p = (p * r) - q;
            q = q * r;
            
            result = cancel(p, q);
            p = result.getFirstValue();
            q = result.getSecondValue();
        }

        if (p > 0)
        {
            System.out.printf("%d/%d", p, q);
        }

        System.out.println();
    }
    
}
