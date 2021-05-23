package controller;

import domain.GroupService;
import domain.PersonService;
import opdracht1.ScoreRepository;

public class ControllerFactory {

	public controller.RequestHandler getController(String key, PersonService model, GroupService groupService, ScoreRepository scoreRepository) {
		return createHandler(key, model, groupService, scoreRepository);
	}
//	public AsyncRequesthandler getAsyncController(String key, PersonService model, GroupService groupService) {
//		return createAsyncHandler(key, model, groupService);
//	}


	private controller.RequestHandler createHandler(String handlerName, PersonService model, GroupService groupService, ScoreRepository scoreRepository) {
		controller.RequestHandler handler = null;
		try {
			Class<?> handlerClass = Class.forName("controller."+ handlerName);
			Object handlerObject = handlerClass.newInstance();
			handler = (controller.RequestHandler) handlerObject;
			handler.setModel(model);
			handler.setGroupService(groupService);
			handler.setScoreRepository(scoreRepository);

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
