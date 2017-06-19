package printforms;

import main.Common;
import superclasses.Entity;
import superclasses.PrintForm;

/**
 * Stub for print form
 * @author Vlad
 *
 */
public class RefUserPF extends PrintForm {
	
	@Override
	public void print(Entity ent) {
		Common.showInfo(null, "" + ent);
	}

}