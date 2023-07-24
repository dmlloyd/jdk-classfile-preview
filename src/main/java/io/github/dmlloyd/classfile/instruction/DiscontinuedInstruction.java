/*
 * Copyright (c) 2023, Oracle and/or its affiliates. All rights reserved.
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
package io.github.dmlloyd.classfile.instruction;

import io.github.dmlloyd.classfile.CodeElement;
import io.github.dmlloyd.classfile.CodeModel;
import io.github.dmlloyd.classfile.Instruction;
import io.github.dmlloyd.classfile.Label;
import io.github.dmlloyd.classfile.Opcode;
import io.github.dmlloyd.classfile.impl.AbstractInstruction;
import io.github.dmlloyd.classfile.impl.Util;

/**
 * Models instruction discontinued from the {@code code} array of a {@code Code}
 * attribute. Delivered as a {@link CodeElement} when traversing the elements of
 * a {@link CodeModel}.
 */
public sealed interface DiscontinuedInstruction extends Instruction {

    /**
     * Models JSR and JSR_W instructions discontinued from the {@code code}
     * array of a {@code Code} attribute since class file version 51.0.
     * Corresponding opcodes will have a {@code kind} of
     * {@link Opcode.Kind#DISCONTINUED_JSR}.  Delivered as a {@link CodeElement}
     * when traversing the elements of a {@link CodeModel}.
     */
    sealed interface JsrInstruction extends DiscontinuedInstruction
            permits AbstractInstruction.BoundJsrInstruction,
                    AbstractInstruction.UnboundJsrInstruction {

        /**
         * {@return the target of the JSR instruction}
         */
        Label target();

        /**
         * {@return a JSR instruction}
         *
         * @param op the opcode for the specific type of JSR instruction,
         *           which must be of kind {@link Opcode.Kind#DISCONTINUED_JSR}
         * @param target target label of the subroutine
         */
        static JsrInstruction of(Opcode op, Label target) {
            Util.checkKind(op, Opcode.Kind.DISCONTINUED_JSR);
            return new AbstractInstruction.UnboundJsrInstruction(op, target);
        }

        /**
         * {@return a JSR instruction}
         *
         * @param target target label of the subroutine
         */
        static JsrInstruction of(Label target) {
            return of(Opcode.JSR, target);
        }
    }

    /**
     * Models RET and RET_W instructions discontinued from the {@code code}
     * array of a {@code Code} attribute since class file version 51.0.
     * Corresponding opcodes will have a {@code kind} of
     * {@link Opcode.Kind#DISCONTINUED_RET}.  Delivered as a {@link CodeElement}
     * when traversing the elements of a {@link CodeModel}.
     */
    sealed interface RetInstruction extends DiscontinuedInstruction
            permits AbstractInstruction.BoundRetInstruction,
                    AbstractInstruction.UnboundRetInstruction {

        /**
         * {@return the local variable slot with return address}
         */
        int slot();

        /**
         * {@return a RET or RET_W instruction}
         *
         * @param op the opcode for the specific type of RET instruction,
         *           which must be of kind {@link Opcode.Kind#DISCONTINUED_RET}
         * @param slot the local variable slot to load return address from
         */
        static RetInstruction of(Opcode op, int slot) {
            Util.checkKind(op, Opcode.Kind.DISCONTINUED_RET);
            return new AbstractInstruction.UnboundRetInstruction(op, slot);
        }

        /**
         * {@return a RET instruction}
         *
         * @param slot the local variable slot to load return address from
         */
        static RetInstruction of(int slot) {
            return of(slot < 256 ? Opcode.RET : Opcode.RET_W, slot);
        }
    }
}