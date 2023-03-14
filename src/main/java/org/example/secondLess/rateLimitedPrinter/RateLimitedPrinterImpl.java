package org.example.secondLess.rateLimitedPrinter;

public class RateLimitedPrinterImpl implements RateLimitedPrinter{
    private final int interval;
    private long currentTime;
    public RateLimitedPrinterImpl(int interval) {
        this.interval = interval;
        this.currentTime = System.currentTimeMillis();
    }
    @Override
    public void print(String message) {
        long newTime = System.currentTimeMillis();
        if (newTime-currentTime > interval){
            System.out.println(message);
            currentTime = newTime;
        }
    }
}
