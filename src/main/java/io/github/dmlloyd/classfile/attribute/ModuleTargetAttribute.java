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

import io.github.dmlloyd.classfile.Attribute;
import io.github.dmlloyd.classfile.ClassElement;
import io.github.dmlloyd.classfile.ClassModel;
import io.github.dmlloyd.classfile.constantpool.Utf8Entry;
import io.github.dmlloyd.classfile.impl.BoundAttribute;
import io.github.dmlloyd.classfile.impl.TemporaryConstantPool;
import io.github.dmlloyd.classfile.impl.UnboundAttribute;
import io.github.dmlloyd.classfile.extras.PreviewFeature;

/**
 * Models the {@code ModuleTarget} attribute, which can
 * appear on classes that represent module descriptors.  This is a JDK-specific
 * attribute, which captures constraints on the target platform.
 * Delivered as a {@link ClassElement} when
 * traversing the elements of a {@link ClassModel}.
 *
 * <p>The specification of the {@code ModuleTarget} attribute is:
 * <pre> {@code
 * TargetPlatform_attribute {
 *   // index to CONSTANT_utf8_info structure in constant pool representing
 *   // the string "ModuleTarget"
 *   u2 attribute_name_index;
 *   u4 attribute_length;
 *
 *   // index to CONSTANT_utf8_info structure with the target platform
 *   u2 target_platform_index;
 * }
 * } </pre>
 * <p>
 * The attribute does not permit multiple instances in a given location.
 * Subsequent occurrence of the attribute takes precedence during the attributed
 * element build or transformation.
 *
 * @since 22
 */
@PreviewFeature(feature = PreviewFeature.Feature.CLASSFILE_API)
public sealed interface ModuleTargetAttribute
        extends Attribute<ModuleTargetAttribute>, ClassElement
        permits BoundAttribute.BoundModuleTargetAttribute, UnboundAttribute.UnboundModuleTargetAttribute {

    /**
     * {@return the target platform}
     */
    Utf8Entry targetPlatform();

    /**
     * {@return a {@code ModuleTarget} attribute}
     * @param targetPlatform the target platform
     */
    static ModuleTargetAttribute of(String targetPlatform) {
        return of(TemporaryConstantPool.INSTANCE.utf8Entry(targetPlatform));
    }

    /**
     * {@return a {@code ModuleTarget} attribute}
     * @param targetPlatform the target platform
     */
    static ModuleTargetAttribute of(Utf8Entry targetPlatform) {
        return new UnboundAttribute.UnboundModuleTargetAttribute(targetPlatform);
    }
}
