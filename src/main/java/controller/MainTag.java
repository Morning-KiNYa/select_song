package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SongDAO;
import dao.TagDAO;
import model.Song;

/**
 * Servlet implementation class MainTag
 */
@WebServlet("/MainTag")
public class MainTag extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MainTag() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		List<Song> list = null;
		int tagId = Integer.parseInt(request.getParameter("tag"));
		//		System.out.println("フォームから"+tagId+"が送られてきました");
		if (tagId == 0) {
			SongDAO dao = new SongDAO();
			list = dao.findAll();
		} else {
			TagDAO dao = new TagDAO();
			list = dao.findTag(tagId);
		}
		//		System.out.println("daoに"+tagId+"を渡しました");
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/view/main.jsp");
		rd.forward(request, response);
	}

}
