/*
 * Copyright (c) 2015 the original author or authors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Etienne Studer & Donát Csikós (Gradle Inc.) - initial API and implementation and initial documentation
 */

package org.eclipse.buildship.ui.console;

import com.google.common.base.Preconditions;
import org.eclipse.buildship.ui.PluginImage.ImageState;
import org.eclipse.buildship.ui.PluginImages;
import org.eclipse.buildship.ui.generic.NodeSelection;
import org.eclipse.buildship.ui.generic.SelectionSpecificAction;
import org.eclipse.buildship.ui.i18n.UiMessages;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.ITreeSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.widgets.Event;

/**
 * Expands all the nodes under the selected node or the entire tree if no node is selected.
 */
public final class ExpandTreeNodesAction extends Action implements SelectionSpecificAction {

    private final AbstractTreeViewer treeViewer;

    public ExpandTreeNodesAction(AbstractTreeViewer treeViewer) {
        this.treeViewer = Preconditions.checkNotNull(treeViewer);

        setToolTipText(UiMessages.Action_ExpandNode_Tooltip);
        setImageDescriptor(PluginImages.EXPAND_NODE.withState(ImageState.ENABLED).getImageDescriptor());
    }

    @Override
    public void runWithEvent(Event event) {
        ITreeSelection selection = this.treeViewer.getStructuredSelection();
        if (selection.isEmpty()) {
            this.treeViewer.expandAll();
        } else {
            for (Object element : selection.toList()) {
                this.treeViewer.expandToLevel(element, TreeViewer.ALL_LEVELS);
            }
        }
    }

    @Override
    public boolean isVisibleFor(NodeSelection selection) {
        return true;
    }

    @Override
    public boolean isEnabledFor(NodeSelection selection) {
        return true;
    }

    @Override
    public void setEnabledFor(NodeSelection selection) {
        setEnabled(true);
    }

}
