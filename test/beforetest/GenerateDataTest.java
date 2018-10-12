package beforetest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import com.thanh.entity.Book;
import com.thanh.entity.Category;
import com.thanh.entity.Exportation;
import com.thanh.entity.Image;
import com.thanh.entity.Importation;
import com.thanh.entity.Manufacturer;
import com.thanh.entity.Order;
import com.thanh.entity.OrdersDetail;
import com.thanh.entity.Promotion;
import com.thanh.entity.PromotionEvent;
import com.thanh.entity.Storage;
import com.thanh.entity.User;
import com.thanh.enumeration.Authority;
import com.thanh.enumeration.Gender;
import com.thanh.enumeration.OrderStatus;
import com.thanh.enumeration.PaymentMethod;

public class GenerateDataTest {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public static List<User> users;
	public static List<Category> categories;
	public static List<Manufacturer> manufacturers;
	public static List<Promotion> promotions;
	public static List<Book> books;
	public static List<Order> orders;
	public static List<Storage> storages;
	public static List<Exportation> exportations;
	public static List<Importation> importations;
	public static List<Image> images;
	public static List<OrdersDetail> orderDetails;
	public static List<PromotionEvent> promotionEvents;

	public static void generateData(JdbcTemplate jdbc) {
		resetDataTest(jdbc);
		generateUserList();
		generateCategoryList();
		generateManufacturerList();
		generatePromotionList();
		generateBookList();
		generateOrderList();
		generateStorageList();
		generateExportationList();
		generateImportationList();
		generateImageList();
		generateOrderDetailList();
		generatePromotionEventList();
	}

	private static void resetDataTest(JdbcTemplate jdbc) {
		jdbc.execute("call resetData()");
	}

	private static void generateUserList() {
		users = new ArrayList<>();
		try {
			users.add(new User(1, "Abreu2022", "63792i397h8s9", "Abdul551", "DeeannaAbreu59@example.com",
					dateFormat.parse("1958-03-09"), Gender.OTHER, "18 S Rose Hill Ln", "868-9304",
					Authority.ROLE_USER));
			users.add(new User(2, "Cathey1980", "9qlzm93055jnsk", "Lizzette466", "ZonaAmaral638@nowhere.com",
					dateFormat.parse("1987-12-08"), Gender.FEMALE, "711 South Riddle Hill Loop", "126-2653",
					Authority.ROLE_USER));
			users.add(new User(3, "Aleta1980", "z03vm1zc", "Alonzo2022", "Addison@example.com",
					dateFormat.parse("1948-01-07"), Gender.MALE, "241 East Riverside Highway", "171-4493",
					Authority.ROLE_USER));
			users.add(new User(4, "Melba2", "u579jq2156hzj613b5", "Abney7", "Abney88@nowhere.com",
					dateFormat.parse("1975-12-27"), Gender.OTHER, "1679 South Lake Lane", "874-7011",
					Authority.ROLE_USER));
			users.add(new User(5, "Vanessa1985", "yd84k1h60z0", "Jarrod2023", "Nicolette_Flores98@nowhere.com",
					dateFormat.parse("1956-02-03"), Gender.OTHER, "394 Pine Tree Cir", "241-1969",
					Authority.ROLE_USER));
			users.add(new User(6, "Wolf35", "e6n5n19no", "Alisa2016", "qjjvmdzc5602@example.com",
					dateFormat.parse("1982-02-06"), Gender.MALE, "552 Red Riverview Circle", "480-1469",
					Authority.ROLE_USER));
			users.add(new User(7, "Gala2015", "7lul8nke8", "Bess1981", "AldaTitus@example.com",
					dateFormat.parse("1950-03-12"), Gender.FEMALE, "492 South Brentwood Ln", "636-8895",
					Authority.ROLE_USER));
			users.add(new User(8, "Buster45", "ji5937kh", "Roark982", "Amaya@example.com",
					dateFormat.parse("1974-04-04"), Gender.OTHER, "256 Oak Court", "645-2382", Authority.ROLE_USER));
			users.add(new User(9, "Aiko86", "597uk07yjat0", "Chadwick842", "AustinClinton@example.com",
					dateFormat.parse("1991-08-24"), Gender.FEMALE, "361 Meadowview Way", "511-2464",
					Authority.ROLE_USER));
			users.add(new User(10, "Abel614", "1jqqd67b", "Alphonso8", "crfipqej_kmjfuiub@example.com",
					dateFormat.parse("1979-03-19"), Gender.FEMALE, "1191 SW Rushwood Ln", "916-2883",
					Authority.ROLE_USER));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void generateCategoryList() {
		categories = new ArrayList<>();
		categories.add(new Category(1, "Tools"));
		categories.add(new Category(2, "Office"));
		categories.add(new Category(3, "Crafts"));
		categories.add(new Category(4, "Accessories"));
		categories.add(new Category(5, "Clothing"));
		categories.add(new Category(6, "Clothing"));
		categories.add(new Category(7, "Books"));
		categories.add(new Category(8, "Food"));
		categories.add(new Category(9, "Books"));
		categories.add(new Category(10, "Audible"));
	}

	private static void generateManufacturerList() {
		manufacturers = new ArrayList<>();
		manufacturers.add(new Manufacturer(1, "International High-Technologies Co."));
		manufacturers.add(new Manufacturer(2, "National Software Corp."));
		manufacturers.add(new Manufacturer(3, "Smart Entertainment Inc."));
		manufacturers.add(new Manufacturer(4, "American High-Technologies Corporation"));
		manufacturers.add(new Manufacturer(5, "General I-Mobile Corporation"));
		manufacturers.add(new Manufacturer(6, "Special High-Technologies Group"));
		manufacturers.add(new Manufacturer(7, "Union Transport Co."));
		manufacturers.add(new Manufacturer(8, "North V-Mobile Inc."));
		manufacturers.add(new Manufacturer(9, "Professional High-Technologies Group"));
		manufacturers.add(new Manufacturer(10, "Canadian Data Inc."));
	}

	private static void generatePromotionList() {
		promotions = new ArrayList<>();
		try {
			promotions.add(new Promotion(1, "Iste accusantium sit laudantium.", dateFormat.parse("2018-10-10"),
					dateFormat.parse("2018-12-31"), 41));
			promotions.add(new Promotion(2, "Porro et tempora suscipit magni.", dateFormat.parse("2018-01-01"),
					dateFormat.parse("2018-01-18"), 76));
			promotions.add(new Promotion(3, "Ipsa voluptatibus natus officia.", dateFormat.parse("2018-10-10"),
					dateFormat.parse("2018-11-11"), 1));
			promotions.add(new Promotion(4, "Odio tempore non a et.", dateFormat.parse("2017-01-02"),
					dateFormat.parse("2017-11-10"), 85));
			promotions.add(new Promotion(5, "Unde qui iusto molestiae aut.", dateFormat.parse("2017-04-09"),
					dateFormat.parse("2018-08-31"), 3));
			promotions.add(new Promotion(6, "Doloremque amet unde amet sunt;", dateFormat.parse("2018-10-09"),
					dateFormat.parse("2018-12-01"), 70));
			promotions.add(new Promotion(7, "Veritatis nisi vero perferendis sed.", dateFormat.parse("2018-03-01"),
					dateFormat.parse("2018-03-02"), 88));
			promotions.add(new Promotion(8, "Et quod perspiciatis sint error...", dateFormat.parse("2018-09-09"),
					dateFormat.parse("2018-11-08"), 15));
			promotions.add(new Promotion(9, "Aliquam maxime dolores omnis ut.", dateFormat.parse("2018-09-19"),
					dateFormat.parse("2018-12-20"), 34));
			promotions.add(new Promotion(10, "Perspiciatis minima illo iusto;", dateFormat.parse("2018-09-19"),
					dateFormat.parse("2018-10-28"), 75));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generateBookList() {
		books = new ArrayList<>();
		books.add(new Book(1, 9, "Letha241", "gaaapjaccsqkwdwyank", 2, "Balduin  Anger", 226300));
		books.add(new Book(2, 4, "Mollie1975", "vwyhimicaaurtfrsrhqxrbn", 3, "Lisa-Marie Spangenberg", 69861));
		books.add(new Book(3, 6, "Quintin5", "fobsgtsqwvjsneqfxk", 1, "Fine Sommer", 365191));
		books.add(new Book(4, 2, "Kathern49", "hfejx", 1, "Friedburg Hildesheimer", 99113));
		books.add(new Book(5, 3, "Felisa221", "djgnjbmsyhnadosgmfdgtyopgbbygm", 2, "Jochem üller", 319386));
		books.add(new Book(6, 9, "Shenita2027", "envqdhkhmuojiqzsbiz", 9, "Tusnelda Bernhard", 318292));
		books.add(new Book(7, 1, "Arnita2004", "oibjxkabszwi", 8, "Django chmitz", 84585));
		books.add(new Book(8, 10, "Woodrow2009", "ntx", 10, "Erhard Kleist", 492852));
		books.add(new Book(9, 6, "Chantel318", "ulzjewysxssohwepz", 3, "Charlena chneider", 412332));
		books.add(new Book(10, 6, "Aisha2010", "papabpaoqsoscx", 2, "Lewin Loos", 246657));
	}

	private static void generateOrderList() {
		orders = new ArrayList<>();
		try {
			orders.add(new Order(1, 10, dateFormat.parse("2017-10-29"), dateFormat.parse("2017-01-02"),
					"1862 Cedar Tree Blvd", 405895, PaymentMethod.COD, OrderStatus.CANCELED));
			orders.add(new Order(2, 10, dateFormat.parse("2017-05-01"), dateFormat.parse("2017-11-08"),
					"14 Waterview Road", 336380, PaymentMethod.COD, OrderStatus.PREPARING));
			orders.add(new Order(3, 1, dateFormat.parse("2017-09-04"), dateFormat.parse("2018-03-07"),
					"635 Flintwood Circle", 474390, PaymentMethod.COD, OrderStatus.CANCELED));
			orders.add(new Order(4, 8, dateFormat.parse("2018-03-17"), dateFormat.parse("2017-10-01"),
					"3178 East Edgewood Cir", 410193, PaymentMethod.COD, OrderStatus.FINISH));
			orders.add(new Order(5, 5, dateFormat.parse("2017-05-05"), dateFormat.parse("2017-02-18"),
					"1871 Hunting Hill Way", 493822, PaymentMethod.COD, OrderStatus.SHIPPING));
			orders.add(new Order(6, 5, dateFormat.parse("2017-11-18"), dateFormat.parse("2018-04-27"),
					"3279 North Hunting Hill Court", 141857, PaymentMethod.INTERNET_BANKING, OrderStatus.SHIPPING));
			orders.add(new Order(7, 7, dateFormat.parse("2017-08-11"), dateFormat.parse("2018-04-08"),
					"818 Brentwood Parkway", 480177, PaymentMethod.COD, OrderStatus.FINISH));
			orders.add(new Order(8, 5, dateFormat.parse("2017-07-17"), dateFormat.parse("2017-02-12"),
					"55 East Monument Pkwy", 447909, PaymentMethod.COD, OrderStatus.FINISH));
			orders.add(new Order(9, 9, dateFormat.parse("2018-06-05"), dateFormat.parse("2017-06-25"),
					"1443 Front Blvd", 388382, PaymentMethod.INTERNET_BANKING, OrderStatus.CANCELED));
			orders.add(new Order(10, 4, dateFormat.parse("2018-02-09"), dateFormat.parse("2017-09-11"),
					"902 Burwood Blvd", 489830, PaymentMethod.COD, OrderStatus.CANCELED));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generateStorageList() {
		storages = new ArrayList<>();
		storages.add(new Storage(1, "Burton92", "2747 Red Town Ct", 5));
		storages.add(new Storage(2, "Andrade718", "947 Flintwood Ct", 5));
		storages.add(new Storage(3, "Stacy1964", "3763 Ski Hill Parkway", 4));
		storages.add(new Storage(4, "Amaya5", "1694 SE Prospect Hill Highway", 1));
		storages.add(new Storage(5, "Ziegler1974", "385 White Chapel Hill Ln", 8));
		storages.add(new Storage(6, "Abbie966", "855 White Edgewood Parkway", 5));
		storages.add(new Storage(7, "Ardis759", "266 Red Hunting Hill Ln", 9));
		storages.add(new Storage(8, "Alita8", "1876 Riddle Hill Blvd", 2));
		storages.add(new Storage(9, "Dung261", "545 Market Way", 5));
		storages.add(new Storage(10, "Aleta1974", "3153 Riverview Hwy", 3));
	}

	private static void generateExportationList() {
		exportations = new ArrayList<>();
		try {
			exportations.add(new Exportation(1, 3, 7, 94, dateFormat.parse("2018-09-25")));
			exportations.add(new Exportation(2, 3, 8, 40, dateFormat.parse("2018-05-17")));
			exportations.add(new Exportation(3, 8, 7, 88, dateFormat.parse("2018-03-27")));
			exportations.add(new Exportation(4, 2, 5, 42, dateFormat.parse("2018-03-03")));
			exportations.add(new Exportation(5, 10, 1, 47, dateFormat.parse("2018-05-13")));
			exportations.add(new Exportation(6, 6, 8, 49, dateFormat.parse("2018-02-16")));
			exportations.add(new Exportation(7, 10, 1, 82, dateFormat.parse("2018-05-04")));
			exportations.add(new Exportation(8, 8, 3, 29, dateFormat.parse("2018-07-22")));
			exportations.add(new Exportation(9, 9, 2, 97, dateFormat.parse("2018-06-17")));
			exportations.add(new Exportation(10, 7, 1, 49, dateFormat.parse("2017-10-21")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generateImportationList() {
		importations = new ArrayList<>();
		try {
			importations.add(new Importation(1, 6, 3, 38, dateFormat.parse("2018-05-15"), 199209));
			importations.add(new Importation(2, 4, 9, 73, dateFormat.parse("2018-04-14"), 316632));
			importations.add(new Importation(3, 6, 2, 47, dateFormat.parse("2018-10-03"), 95556));
			importations.add(new Importation(4, 3, 8, 48, dateFormat.parse("2018-08-10"), 279008));
			importations.add(new Importation(5, 8, 2, 14, dateFormat.parse("2018-03-08"), 342639));
			importations.add(new Importation(6, 5, 3, 12, dateFormat.parse("2017-01-01"), 337340));
			importations.add(new Importation(7, 3, 4, 42, dateFormat.parse("2018-07-21"), 332171));
			importations.add(new Importation(8, 5, 4, 6, dateFormat.parse("2017-01-14"), 189181));
			importations.add(new Importation(9, 6, 9, 62, dateFormat.parse("2017-01-05"), 258494));
			importations.add(new Importation(10, 2, 1, 49, dateFormat.parse("2017-01-03"), 53009));
		} catch (

		ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void generateImageList() {
		images = new ArrayList<>();
		images.add(new Image(1, 2, "book2"));
		images.add(new Image(2, 1, "book70"));
		images.add(new Image(3, 7, "book02"));
		images.add(new Image(4, 2, "book67"));
		images.add(new Image(5, 9, "book20"));
		images.add(new Image(6, 10, "book3"));
		images.add(new Image(7, 6, "book96"));
		images.add(new Image(8, 8, "book83"));
		images.add(new Image(9, 7, "book43"));
		images.add(new Image(10, 6, "book40"));
		images.add(new Image(11, 8, "book52"));
		images.add(new Image(12, 4, "book95"));
		images.add(new Image(13, 3, "book0"));
		images.add(new Image(14, 10, "book28"));
		images.add(new Image(15, 3, "book93"));
		images.add(new Image(16, 7, "book68"));
		images.add(new Image(17, 3, "book6"));
		images.add(new Image(18, 8, "book3"));
		images.add(new Image(19, 2, "book0"));
		images.add(new Image(20, 5, "book77"));
	}

	private static void generateOrderDetailList() {
		orderDetails = new ArrayList<>();
		orderDetails.add(new OrdersDetail(9, 10, 7, 261027));
		orderDetails.add(new OrdersDetail(2, 3, 9, 94324));
		orderDetails.add(new OrdersDetail(7, 7, 8, 177915));
		orderDetails.add(new OrdersDetail(5, 6, 2, 279337));
		orderDetails.add(new OrdersDetail(10, 10, 5, 175647));
		orderDetails.add(new OrdersDetail(8, 8, 3, 269102));
		orderDetails.add(new OrdersDetail(3, 3, 6, 56942));
		orderDetails.add(new OrdersDetail(1, 1, 8, 116610));
		orderDetails.add(new OrdersDetail(4, 4, 9, 162790));
		orderDetails.add(new OrdersDetail(6, 6, 6, 272101));
		orderDetails.add(new OrdersDetail(2, 2, 7, 161739));
		orderDetails.add(new OrdersDetail(9, 9, 3, 424189));
		orderDetails.add(new OrdersDetail(10, 1, 4, 222750));
		orderDetails.add(new OrdersDetail(6, 7, 4, 159090));
		orderDetails.add(new OrdersDetail(8, 9, 9, 157855));
		orderDetails.add(new OrdersDetail(7, 8, 2, 235397));
		orderDetails.add(new OrdersDetail(3, 4, 6, 179821));
		orderDetails.add(new OrdersDetail(5, 5, 4, 230652));
		orderDetails.add(new OrdersDetail(1, 2, 9, 298582));
		orderDetails.add(new OrdersDetail(4, 5, 8, 214025));
	}

	private static void generatePromotionEventList() {
		promotionEvents = new ArrayList<>();
		promotionEvents.add(new PromotionEvent(1, 8, 5, 1));
		promotionEvents.add(new PromotionEvent(2, 2, 6, 2));
		promotionEvents.add(new PromotionEvent(3, 7, 5, 3));
		promotionEvents.add(new PromotionEvent(4, 1, 10, 4));
		promotionEvents.add(new PromotionEvent(5, 3, 10, 5));
		promotionEvents.add(new PromotionEvent(6, 7, 9, 6));
		promotionEvents.add(new PromotionEvent(7, 3, 4, 7));
		promotionEvents.add(new PromotionEvent(8, 7, 4, 8));
		promotionEvents.add(new PromotionEvent(9, 1, 2, 9));
		promotionEvents.add(new PromotionEvent(10, 2, 7, 10));
	}
}
