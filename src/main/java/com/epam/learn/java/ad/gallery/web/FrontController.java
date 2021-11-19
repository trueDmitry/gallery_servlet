package com.epam.learn.java.ad.gallery.web;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.learn.java.ad.gallery.api.ServiceProviderI;
import com.epam.learn.java.ad.gallery.app.ApplicationContext;
import com.epam.learn.java.ad.gallery.app.ServiceProvider;
import com.epam.learn.java.ad.gallery.app.exception.AccessException;
import com.epam.learn.java.ad.gallery.model.User;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("/web/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FrontController() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatch(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		dispatch(request, response);
	}

	protected void dispatch(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		new CommandBuilder(request, response).getCommand().run();
	}

	class CommandBuilder {
		private final StringBuilder pack = new StringBuilder(this.getClass().getPackageName());

		private WebCommand command;

		public CommandBuilder(HttpServletRequest request, HttpServletResponse response) {
			command = createCommand(getCommandFQN(request));
			
			command.init(FrontController.this.getServletContext(), request, response);
		}

		public WebCommand getCommand() {
			return command;
		}

		private String getCommandFQN(HttpServletRequest request) {
			String path = request.getAttribute("originalPath").toString();
			return path.equals("/") ? "/Default" : path;
		}

		private WebCommand createCommand(String name) {
			String className = new StringBuilder(pack).append(name.replace('/', '.')).toString();

			WebCommand command;
			try {
				command = Class.forName(className).asSubclass(WebCommand.class).getDeclaredConstructor()
						.newInstance();
			} catch (Exception e) {
				name = "404";
				command = new ResourseNotFoundCommand();
			}
			command.setName(name);
			return command;

		}
	}

}
