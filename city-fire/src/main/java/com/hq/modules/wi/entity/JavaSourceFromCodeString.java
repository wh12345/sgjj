package com.hq.modules.wi.entity;

import javax.tools.SimpleJavaFileObject;
import java.net.URI;

/**
 * Created by Because of you on 2019/11/22.
 */
public class JavaSourceFromCodeString  extends SimpleJavaFileObject {
        /**
         * The source code of this "file".
         */
        final String code;

        /**
         * Constructs a new JavaSourceFromString.
         * @param name the name of the compilation unit represented by this file object
         * @param code the source code for the compilation unit represented by this file object
         */
        public JavaSourceFromCodeString(String name, String code) {
            super(URI.create("string:///" + name.replace('.','/') + Kind.SOURCE.extension),
                    Kind.SOURCE);
            this.code = code;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) {
            return code;
        }
}
