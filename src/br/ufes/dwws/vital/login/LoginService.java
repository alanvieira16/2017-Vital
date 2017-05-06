package br.ufes.dwws.vital.login;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.EJBTransactionRolledbackException;
import javax.ejb.LocalBean;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import br.ufes.dwws.vital.domain.User;
import br.ufes.dwws.vital.persistence.UserDAO;
import br.ufes.inf.nemo.jbutler.TextUtils;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.MultiplePersistentObjectsFoundException;
import br.ufes.inf.nemo.jbutler.ejb.persistence.exceptions.PersistentObjectNotFoundException;

@Stateless
@LocalBean
public class LoginService implements Serializable {

	private static final long serialVersionUID = 1L;

	@Resource
	private SessionContext sessionContext;

	@EJB
	private UserDAO userDAO;

	public void login(String email, String password) throws LoginFailedException {

		try {

			User user = userDAO.retrieveByEmail(email);
			
			String md5pwd = TextUtils.produceBase64EncodedMd5Hash(password);
			String pwd = user.getPassword();

			if (pwd == null || !pwd.equals(md5pwd))
				throw new LoginFailedException(LoginFailedException.LoginFailedReason.INCORRECT_PASSWORD);

		} catch (PersistentObjectNotFoundException e) {
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.UNKNOWN_USERNAME);
		} catch (MultiplePersistentObjectsFoundException e) {
			throw new LoginFailedException(e, LoginFailedException.LoginFailedReason.MULTIPLE_USERS);
		} catch (EJBTransactionRolledbackException e) {
			throw e;
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			throw new LoginFailedException(LoginFailedException.LoginFailedReason.MD5_ERROR);
		}

	}

	public User getCurrentUser() {
		try {
			return userDAO.retrieveByEmail(sessionContext.getCallerPrincipal().getName());
		} catch (PersistentObjectNotFoundException | MultiplePersistentObjectsFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
