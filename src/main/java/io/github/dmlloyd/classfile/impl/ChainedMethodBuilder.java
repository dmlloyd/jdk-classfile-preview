/*
 * Copyright (c) 2022, 2024, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package io.github.dmlloyd.classfile.impl;

import java.util.function.Consumer;

import io.github.dmlloyd.classfile.CodeBuilder;
import io.github.dmlloyd.classfile.CodeModel;
import io.github.dmlloyd.classfile.CodeTransform;
import io.github.dmlloyd.classfile.MethodBuilder;
import io.github.dmlloyd.classfile.MethodElement;
import io.github.dmlloyd.classfile.constantpool.ConstantPoolBuilder;

public final class ChainedMethodBuilder implements MethodBuilder {
    final TerminalMethodBuilder terminal;
    final Consumer<MethodElement> consumer;

    public ChainedMethodBuilder(MethodBuilder downstream,
                                Consumer<MethodElement> consumer) {
        this.consumer = consumer;
        this.terminal = downstream instanceof ChainedMethodBuilder cb ? cb.terminal : (TerminalMethodBuilder) downstream;
    }

    @Override
    public MethodBuilder with(MethodElement element) {
        consumer.accept(element);
        return this;
    }

    @Override
    public MethodBuilder withCode(Consumer<? super CodeBuilder> handler) {
        consumer.accept(terminal.bufferedCodeBuilder(null)
                                       .run(handler)
                                       .toModel());
        return this;
    }

    @Override
    public MethodBuilder transformCode(CodeModel code, CodeTransform transform) {
        BufferedCodeBuilder builder = terminal.bufferedCodeBuilder(code);
        builder.transform(code, transform);
        consumer.accept(builder.toModel());
        return this;
    }

    @Override
    public ConstantPoolBuilder constantPool() {
        return terminal.constantPool();
    }

}
