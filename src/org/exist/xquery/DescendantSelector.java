/*
 *  eXist Open Source Native XML Database
 *  Copyright (C) 2001-03 Wolfgang M. Meier
 *  wolfgang@exist-db.org
 *  http://exist.sourceforge.net
 *  
 *  This program is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public License
 *  as published by the Free Software Foundation; either version 2
 *  of the License, or (at your option) any later version.
 *  
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU Lesser General Public License for more details.
 *  
 *  You should have received a copy of the GNU Lesser General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 *  
 *  $Id$
 */
package org.exist.xquery;

import org.exist.dom.DocumentImpl;
import org.exist.dom.NodeProxy;
import org.exist.dom.NodeSet;


/**
 * @author Wolfgang Meier (wolfgang@exist-db.org)
 */
public class DescendantSelector implements NodeSelector {
	
	protected NodeSet context;
	protected boolean rememberContext = false;
	
	public DescendantSelector(NodeSet contextSet, boolean rememberContext) {
		this.context = contextSet;
		this.rememberContext = rememberContext;
	}
	
	
	/* (non-Javadoc)
	 * @see org.exist.xquery.NodeSelector#match(org.exist.dom.NodeProxy)
	 */
	public NodeProxy match(DocumentImpl doc, long gid) {
        NodeProxy p = new NodeProxy(doc, gid);  
        if (p == null) 
            return null;   
		NodeProxy contextNode = context.parentWithChild(doc, gid, false, false, NodeProxy.UNKNOWN_NODE_LEVEL);
		if (contextNode == null)
            return null;	    
		if (rememberContext)
            p.addContextNode(contextNode);
		else
            p.copyContext(contextNode);
		return p;
	}
}
