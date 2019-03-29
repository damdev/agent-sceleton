package no.blogspot.mydailyjava.agent;

import net.bytebuddy.asm.Advice;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.util.concurrent.Callable;

public class DamInterceptor {
    @Advice.OnMethodExit
    public static void onEnter(@Advice.Argument(0) String aaa, @Advice.Return(readOnly = false) String r) {
        try {
            r = "Lalala: " + r + aaa;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
