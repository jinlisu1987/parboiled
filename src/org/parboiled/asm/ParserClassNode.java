/*
 * Copyright (C) 2009 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.parboiled.asm;

import org.jetbrains.annotations.NotNull;
import org.objectweb.asm.Type;
import org.objectweb.asm.tree.ClassNode;
import org.parboiled.common.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public class ParserClassNode extends ClassNode {

    public final List<Type> ownerTypes;

    public ParserClassNode(@NotNull Class<?> parserClass) {
        List<Type> ownerTypes = new ArrayList<Type>();
        do {
            ownerTypes.add(Type.getType(parserClass));
            parserClass = parserClass.getSuperclass();
        } while (!Object.class.equals(parserClass));
        this.ownerTypes = ImmutableList.copyOf(ownerTypes);
    }

    public Type getType() {
        return ownerTypes.get(0);
    }

}