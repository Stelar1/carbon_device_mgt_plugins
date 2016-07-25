package serviceImp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import service.UserService;

@Service
public class UserServiceImp implements UserService {
	
	@Resource
	private UserDao dao;

	@Override
	public int addUser(User user) {
		return dao.addUser(user);
	}

}
