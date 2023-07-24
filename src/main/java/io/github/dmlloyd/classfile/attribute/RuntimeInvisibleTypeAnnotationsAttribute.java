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

package io.github.dmlloyd.classfile.attribute;

import java.util.List;

import io.github.dmlloyd.classfile.Attribute;
import io.github.dmlloyd.classfile.ClassElement;
import io.github.dmlloyd.classfile.CodeElement;
import io.github.dmlloyd.classfile.FieldElement;
import io.github.dmlloyd.classfile.MethodElement;
import io.github.dmlloyd.classfile.TypeAnnotation;
import io.github.dmlloyd.classfile.impl.BoundAttribute;
import io.github.dmlloyd.classfile.impl.UnboundAttribute;

/**
 * Models the {@code RuntimeInvisibleTypeAnnotations} attribute {@jvms 4.7.21}, which
 * can appear on classes, methods, fields, and code attributes. Delivered as a
 * {@link ClassElement}, {@link FieldElement},
 * {@link MethodElement}, or {@link CodeElement} when traversing
 * the corresponding model type.
 */
public sealed interface RuntimeInvisibleTypeAnnotationsAttribute
        extends Attribute<RuntimeInvisibleTypeAnnotationsAttribute>,
                ClassElement, MethodElement, FieldElement, CodeElement
        permits BoundAttribute.BoundRuntimeInvisibleTypeAnnotationsAttribute,
                UnboundAttribute.UnboundRuntimeInvisibleTypeAnnotationsAttribute {

    /**
     * {@return the non-runtime-visible type annotations on parts of this class, field, or method}
     */
    List<TypeAnnotation> annotations();

    /**
     * {@return a {@code RuntimeInvisibleTypeAnnotations} attribute}
     * @param annotations the annotations
     */
    static RuntimeInvisibleTypeAnnotationsAttribute of(List<TypeAnnotation> annotations) {
        return new UnboundAttribute.UnboundRuntimeInvisibleTypeAnnotationsAttribute(annotations);
    }

    /**
     * {@return a {@code RuntimeInvisibleTypeAnnotations} attribute}
     * @param annotations the annotations
     */
    static RuntimeInvisibleTypeAnnotationsAttribute of(TypeAnnotation... annotations) {
        return of(List.of(annotations));
    }
}