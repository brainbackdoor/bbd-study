package mvc;

import java.io.BufferedReader;
import java.io.IOException;

import db.DataBase;
import webserver.HttpRequest;
import webserver.HttpResponse;

public class UserListController implements Controller {
	DataBase db ;
	@Override
	public void execute(HttpRequest request, HttpResponse response) throws IOException  {
		response.generateListUserHeader();
		response.responseBody();
		this.db = request.getDb();

	}
	@Override
	public void inputParameter(HttpRequest request, BufferedReader br) throws IOException {
	}
	public DataBase syncDataBase() {
		return db;
	}
}
