package controller;

import domain.GroupService;
import domain.PersonService;

public class ControllerFactory {
	
    public RequestHandler getController(String key, PersonService model, GroupService groupService) {
        return createHandler(key, model, groupService);
    }
//	public AsyncRequesthandler getAsyncController(String key, PersonService model, GroupService groupService) {
//		return createAsyncHandler(key, model, groupService);
//	}


	private RequestHandler createHandler(String handlerName, PersonService model, GroupService groupService) {
		RequestHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (RequestHandler) handlerObject;
	    	handler.setModel(model);
	    	handler.setGroupService(groupService);
		} catch (Exception e) {
			throw new RuntimeException("Deze pagina bestaat niet!!!!");
		}
		return handler;
	}

//	private AsyncRequesthandler createAsyncHandler(String handlerName, PersonService model, GroupService groupService) {
//		AsyncRequesthandler handler = null;
//		try {
//			Class<?> handlerClass = Class.forName("controller."+ handlerName);
//			Object handlerObject = handlerClass.newInstance();
//			handler = (AsyncRequesthandler) handlerObject;
//			handler.setModel(model);
//			handler.setGroupService(groupService);
//		} catch (Exception e) {
//			throw new RuntimeException("Deze pagina bestaat niet!!!!");
//		}
//		return handler;
//	}


}
