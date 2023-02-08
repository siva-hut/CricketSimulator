package com.circketApplication;
import com.circketApplication.dao.entities.PlayerDb;
import com.circketApplication.dao.entities.TeamDb;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class App {

	public static void main(String[] args) {
		SpringApplication.run(App.class,args);
		Configuration con = new Configuration().configure().addAnnotatedClass(PlayerDb.class).addAnnotatedClass(TeamDb.class);
		SessionFactory sf = con.buildSessionFactory();
		EntityManager em = sf.openSession();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			TeamDb p = new TeamDb();
			p.setName("csk");
			em.persist(p);
			PlayerDb pd = new PlayerDb("siva",p);
			em.persist(pd);
			System.out.println("1");
			em.flush();
			System.out.println("1");
			tx.commit();
		} catch (Exception exp) {
			tx.rollback();
		}
		em.close();
		sf.close();
//		GameController.addGame(g);
	}

}
