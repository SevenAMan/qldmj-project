package org.qldmj.metacode.janino;

import org.codehaus.commons.compiler.CompileException;
import org.codehaus.janino.SimpleCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class TestJanino {

    private static final Logger LOG = LoggerFactory.getLogger(TestJanino.class);

    private final static String dynamicCode = """
            import org.qldmj.metacode.janino.IToString;
            import java.util.List;
            import java.util.ArrayList;
            
            public class ListToString implements IToString {
                @Override
                public String getListString(List<String> list) {
                    List<String> subList = new ArrayList<String>();
                    subList.add("sub");
                    subList.add("sub2");
                    list.addAll(subList);
                    String l1 = (String)subList.get(0);
                    System.out.println(l1);
                    return String.join(", ", list);
                }
            }
            """;

    public static void main(String[] args) throws CompileException, ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var compiler = new SimpleCompiler();
        compiler.cook(dynamicCode);
        Class<?> aClass = compiler.getClassLoader().loadClass("ListToString");
        IToString i = (IToString) aClass.getConstructor().newInstance();
        var list = new ArrayList<>(List.of("123", "345", "567"));
        var string = i.getListString(list);
        LOG.info(string);
    }
}
