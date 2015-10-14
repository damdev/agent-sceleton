package no.blogspot.mydailyjava.agent;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;

import java.lang.instrument.Instrumentation;

import static net.bytebuddy.matcher.ElementMatchers.*;

public class AgentMain {

    public static void premain(String args, Instrumentation inst) {
        // TODO: Your agent code! (add the listener for debugging purposes)
        new AgentBuilder.Default()
                .withListener(new ErrorListener())
                .rebase(not(nameStartsWith("net.bytebuddy.")), not(isBootstrapClassLoader()))
                .transform(new AgentBuilder.Transformer() {
                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription) {
                        return builder;
                    }
                }).installOn(inst);
    }

    private static class ErrorListener implements AgentBuilder.Listener {

        @Override
        public void onTransformation(TypeDescription typeDescription, DynamicType dynamicType) {
            System.err.println("Transforming: " + typeDescription);
        }

        @Override
        public void onError(String typeName, Throwable throwable) {
            System.err.println("Could not instrument: " + typeName);
            throwable.printStackTrace(System.err);
        }

        @Override
        public void onIgnored(String typeName) {

        }

        @Override
        public void onComplete(String typeName) {

        }
    }

}
