/*******************************************************************************
 * Copyright (c) 2016 Bruno Medeiros and other Contributors.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Bruno Medeiros - initial API and implementation
 *******************************************************************************/
package melnorme.lang.tooling.common.ops;

import melnorme.utilbox.concurrency.OperationCancellation;
import melnorme.utilbox.core.CommonException;

public interface CommonOperation {
	
	void execute(IOperationMonitor om) throws CommonException, OperationCancellation;
	
	public static CommonOperation NULL_COMMON_OPERATION = (pm) -> { };
	
	/* -----------------  ----------------- */
	
	default CommonOperation namedOperation(String taskName) {
		return namedOperation(taskName, this);
	}
	
	public static CommonOperation namedOperation(String taskName, CommonOperation subOp) {
		return new CommonOperation() {
			@Override
			public void execute(IOperationMonitor om) throws CommonException, OperationCancellation {
				om.runSubTask(taskName, subOp);
			}
		};
	}
	
}