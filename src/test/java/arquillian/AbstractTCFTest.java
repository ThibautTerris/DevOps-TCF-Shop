package arquillian;



import fr.unice.polytech.isa.tcf.ControlledPayment;
import fr.unice.polytech.isa.tcf.Signal;
import fr.unice.polytech.isa.tcf.components.CartBean;
import fr.unice.polytech.isa.tcf.components.CustomerRegistryBean;
import fr.unice.polytech.isa.tcf.components.carts.CartStatefulBean;
import fr.unice.polytech.isa.tcf.entities.Cookies;
import fr.unice.polytech.isa.tcf.entities.Customer;
import fr.unice.polytech.isa.tcf.exceptions.AlreadyExistingCustomerException;

import fr.unice.polytech.isa.tcf.exceptions.PaymentException;
import fr.unice.polytech.isa.tcf.interceptors.LogParameters;
import fr.unice.polytech.isa.tcf.managed.CustomerBean;

import fr.unice.polytech.isa.tcf.utils.BankAPI;
import fr.unice.polytech.isa.tcf.utils.Database;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.ClassLoaderAsset;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;

import javax.ejb.EJB;

public abstract class AbstractTCFTest {


	@EJB
	protected Database memory;

	@Deployment
	public static WebArchive createDeployment() {
		// Building a Web ARchive (WAR) containing the following elements:
		return ShrinkWrap.create(WebArchive.class)
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Utils
				.addPackage(Database.class.getPackage())
				// Entities
				.addPackage(Customer.class.getPackage())
				// Entities
				.addPackage(Cookies.class.getPackage())
				// Components Interfaces
				.addPackage(CustomerBean.class.getPackage())
				// Components Interfaces
				.addPackage(CartBean.class.getPackage())
                .addPackage(ControlledPayment.class.getPackage())
				//Components Interfaces
				.addPackage(CartStatefulBean.class.getPackage())
				// Components Interfaces
				.addPackage(CustomerRegistryBean.class.getPackage())
				// Interceptors
				.addPackage(LogParameters.class.getPackage())
				// Exceptions
				.addPackage(PaymentException.class.getPackage())
				// Exceptions
				.addPackage(Signal.class.getPackage())
				//BankApi
				.addPackage(BankAPI.class.getPackage())
				// Persistence file
				.addAsManifestResource(new ClassLoaderAsset("META-INF/persistence.xml"), "persistence.xml");
	}

}

