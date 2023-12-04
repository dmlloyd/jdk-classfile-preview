/*
 * Copyright (c) 2022, Oracle and/or its affiliates. All rights reserved.
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

package io.github.dmlloyd.classfile;

import io.github.dmlloyd.classfile.impl.AbstractInstruction;
import io.github.dmlloyd.classfile.instruction.ArrayLoadInstruction;
import io.github.dmlloyd.classfile.instruction.ArrayStoreInstruction;
import io.github.dmlloyd.classfile.instruction.BranchInstruction;
import io.github.dmlloyd.classfile.instruction.ConstantInstruction;
import io.github.dmlloyd.classfile.instruction.ConvertInstruction;
import io.github.dmlloyd.classfile.instruction.DiscontinuedInstruction;
import io.github.dmlloyd.classfile.instruction.FieldInstruction;
import io.github.dmlloyd.classfile.instruction.IncrementInstruction;
import io.github.dmlloyd.classfile.instruction.InvokeDynamicInstruction;
import io.github.dmlloyd.classfile.instruction.InvokeInstruction;
import io.github.dmlloyd.classfile.instruction.LoadInstruction;
import io.github.dmlloyd.classfile.instruction.LookupSwitchInstruction;
import io.github.dmlloyd.classfile.instruction.MonitorInstruction;
import io.github.dmlloyd.classfile.instruction.NewMultiArrayInstruction;
import io.github.dmlloyd.classfile.instruction.NewObjectInstruction;
import io.github.dmlloyd.classfile.instruction.NewPrimitiveArrayInstruction;
import io.github.dmlloyd.classfile.instruction.NewReferenceArrayInstruction;
import io.github.dmlloyd.classfile.instruction.NopInstruction;
import io.github.dmlloyd.classfile.instruction.OperatorInstruction;
import io.github.dmlloyd.classfile.instruction.ReturnInstruction;
import io.github.dmlloyd.classfile.instruction.StackInstruction;
import io.github.dmlloyd.classfile.instruction.StoreInstruction;
import io.github.dmlloyd.classfile.instruction.TableSwitchInstruction;
import io.github.dmlloyd.classfile.instruction.ThrowInstruction;
import io.github.dmlloyd.classfile.instruction.TypeCheckInstruction;
import io.github.dmlloyd.classfile.extras.PreviewFeature;

/**
 * Models an executable instruction in a method body.
 *
 * @since 22
 */
@PreviewFeature(feature = PreviewFeature.Feature.CLASSFILE_API)
public sealed interface Instruction extends CodeElement
        permits ArrayLoadInstruction, ArrayStoreInstruction, BranchInstruction,
                ConstantInstruction, ConvertInstruction, DiscontinuedInstruction,
                FieldInstruction, InvokeDynamicInstruction, InvokeInstruction,
                LoadInstruction, StoreInstruction, IncrementInstruction,
                LookupSwitchInstruction, MonitorInstruction, NewMultiArrayInstruction,
                NewObjectInstruction, NewPrimitiveArrayInstruction, NewReferenceArrayInstruction,
                NopInstruction, OperatorInstruction, ReturnInstruction,
                StackInstruction, TableSwitchInstruction,
                ThrowInstruction, TypeCheckInstruction, AbstractInstruction {

    /**
     * {@return the opcode of this instruction}
     */
    Opcode opcode();

    /**
     * {@return the size in bytes of this instruction}
     */
    int sizeInBytes();
}
