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
import model.Song;

/**
 * Servlet implementation class AdminUpdate
 */
@WebServlet("/Admin/Update")
public class AdminUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminUpdate() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		if (id == null) {
			response.sendRedirect("/karaoke_/Admin");
			return;
		}
		SongDAO dao = new SongDAO();
		Song song = dao.findOne(Integer.parseInt(id));
		request.setAttribute("song", song);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/update.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String artist = request.getParameter("artist");
		String link = request.getParameter("link");
		String artwork = request.getParameter("artwork");
		String progress = request.getParameter("progress");
		SongDAO dao = new SongDAO();
		Song song = new Song(Integer.parseInt(id),title,artist,link,artwork,progress);
		dao.updateOne(song);
		List<Song>list = dao.findAll();
		request.setAttribute("list", list);
		request.setAttribute("msg", "更新しました");
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/main.jsp");
		rd.forward(request, response);
	}

}
