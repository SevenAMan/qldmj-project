import kotlin.jvm.internal.ReflectionFactory;
import org.jetbrains.annotations.NotNull;
import org.qldmj.proxy.CmdLine;

import java.lang.reflect.Proxy;

public class ProxyTest {

    public static class CmdLine2 implements CmdLine {
        @Override
        public void run(@NotNull String cmd) {
            System.out.println("run metamethod : " + cmd);
        }
    }

    public static CmdLine proxyObject(CmdLine cmdLine) {
        return (CmdLine) Proxy.newProxyInstance(cmdLine.getClass().getClassLoader(), new Class[]{CmdLine.class},
                (proxy, method, args) -> {
                    System.out.println("Proxy: " + method.getName());
                    return method.invoke(cmdLine, args);
                });
    }

    public static void main(String[] args) {
        proxyObject(new CmdLine2()).run("cd ./");
    }
}

