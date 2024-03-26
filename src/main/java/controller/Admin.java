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
 * Servlet implementation class Admin
 */
@WebServlet("/Admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Admin() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SongDAO dao = new SongDAO();
		List<Song> list = dao.findAll();
		request.setAttribute("list", list);
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/admin/main.jsp");
		rd.forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String title = request.getParameter("title");
		String artist = request.getParameter("artist");
		String link = request.getParameter("link");
		String artwork = request.getParameter("artwork");
		String progress = request.getParameter("progress");
//		System.out.println("リクエストパラメータからデータの取得完了");
		SongDAO dao = new SongDAO();
//		System.out.println("DAOの接続完了");
		Song song = new Song(title,artist,link,artwork,progress);
//		System.out.println(song.getTitle() + "インスタンスの生成完了");
		dao.insertOne(song);
//		System.out.println("楽曲リストへの追加完了");
		request.setAttribute("msg","追加しました");
		doGet(request,response);
	}

}
