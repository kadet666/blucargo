package com.blusoft.blucargo.gwt.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.blusoft.blucargo.model.CarBody;
import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.City;
import com.blusoft.blucargo.model.Country;
import com.blusoft.blucargo.model.OfferType;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.Panel;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TabPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class OfferList implements EntryPoint {

	ListDataProvider<CargoOffer> dataProvider = new ListDataProvider<CargoOffer>();

	public void onModuleLoad() {

		final CargoOfferGWTServiceAsync cargoOfferService = (CargoOfferGWTServiceAsync) GWT.create(CargoOfferGWTService.class);
		final CountryGWTServiceAsync countryService = (CountryGWTServiceAsync) GWT.create(CountryGWTService.class);
		final CityGWTServiceAsync cityService = (CityGWTServiceAsync) GWT.create(CityGWTService.class);
		final CarBodyGWTServiceAsync carBodyService = (CarBodyGWTServiceAsync) GWT.create(CarBodyGWTService.class);

		final List<CargoOffer> cargoOffers = new ArrayList<CargoOffer>();

		final CellTable<CargoOffer> table = createCargoOfferTable();

		dataProvider.addDataDisplay(table);

		getCargoOffersAndPopulateTable(cargoOfferService, cargoOffers, table);

		final PushButton startButton = new PushButton(new Image("images/blucargo/menu_start1.jpg"));
		final PushButton vehicleButton = new PushButton(new Image("images/blucargo/menu_pojazd1.jpg"));
		final PushButton cargoButton = new PushButton(new Image("images/blucargo/menu_ladunek1.jpg"));
		final PushButton chatsButton = new PushButton(new Image("images/blucargo/menu_rozmowy1.jpg"));
		final PushButton debtorsButton = new PushButton(new Image("images/blucargo/menu_dluznicy1.jpg"));
		final PushButton companyRankingButton = new PushButton(new Image("images/blucargo/menu_ranking1.jpg"));
		final PushButton settingsButton = new PushButton(new Image("images/blucargo/menu_ustawienia1.jpg"));
		final PushButton helpButton = new PushButton(new Image("images/blucargo/menu_pomoc1.jpg"));

		startButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_start2.jpg"));
		vehicleButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_pojazd2.jpg"));
		cargoButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_ladunek2.jpg"));
		chatsButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_rozmowy2.jpg"));
		debtorsButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_dluznicy2.jpg"));
		companyRankingButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_ranking2.jpg"));
		settingsButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_ustawienia2.jpg"));
		helpButton.getUpHoveringFace().setImage(new Image("images/blucargo/menu_pomoc2.jpg"));

		startButton.setStyleName("menuButton");
		vehicleButton.setStyleName("menuButton");
		cargoButton.setStyleName("menuButton");
		chatsButton.setStyleName("menuButton");
		debtorsButton.setStyleName("menuButton");
		companyRankingButton.setStyleName("menuButton");
		settingsButton.setStyleName("menuButton");
		helpButton.setStyleName("menuButton");

		TabPanel tabPanel = new TabPanel();
		tabPanel.getElement().getStyle().setMarginBottom(10.0, Unit.PX);

		final ListBox departureCountries = new ListBox(false);
		final ListBox arrivalCountries = new ListBox(false);
		final ListBox departureCities = new ListBox(false);
		final ListBox arrivalCities = new ListBox(false);
		final ListBox bodies = new ListBox(false);
		bodies.addItem("Nadwozie");

		final ListBox weight = new ListBox(false);
		weight.addItem("Waga");

		final AsyncCallback departureCitiesCallback = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// do some UI stuff to show failure
			}

			public void onSuccess(Object result) {

				departureCities.addItem("Miasto wyjazdu");

				List<City> list = (List<City>) result;
				for (City city : list) {
					departureCities.addItem(city.getCity(), city.getId().toString());
				}

			}
		};

		final AsyncCallback arrivalCitiesCallback = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// do some UI stuff to show failure
			}

			public void onSuccess(Object result) {

				arrivalCities.addItem("Miasto przyjazdu");

				List<City> list = (List<City>) result;
				for (City city : list) {
					arrivalCities.addItem(city.getCity(), city.getId().toString());
				}

			}
		};

		departureCountries.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {

				String countryCode = departureCountries.getValue(departureCountries.getSelectedIndex());
				cityService.findCitiesByCountry(countryCode, departureCitiesCallback);
			}

		});

		arrivalCountries.addChangeHandler(new ChangeHandler() {

			public void onChange(ChangeEvent event) {

				String countryCode = arrivalCountries.getValue(arrivalCountries.getSelectedIndex());
				cityService.findCitiesByCountry(countryCode, arrivalCitiesCallback);
			}

		});

		populateDepartureAndArrivalCountries(countryService, departureCountries, arrivalCountries);
		populateBodies(carBodyService, bodies);

		HorizontalPanel searchPanel1 = new HorizontalPanel();
		HorizontalPanel searchPanel2 = new HorizontalPanel();
		searchPanel1.add(departureCountries);
		searchPanel1.add(arrivalCountries);
		searchPanel1.add(bodies);

		searchPanel2.add(departureCities);
		searchPanel2.add(arrivalCities);
		searchPanel2.add(weight);

		Panel mainPanel = new FlowPanel();
		mainPanel.add(searchPanel1);
		mainPanel.add(searchPanel2);

		mainPanel.add(table);

		tabPanel.add(mainPanel, "");
		tabPanel.add(vehicleButton, "");
		tabPanel.add(cargoButton, "");
		tabPanel.add(chatsButton, "");
		tabPanel.add(debtorsButton, "");
		tabPanel.add(companyRankingButton, "");
		tabPanel.add(settingsButton, "");
		tabPanel.add(helpButton, "");

		// RootPanel.get("offerListArea").add(horizontalPanel);
		// RootPanel.get("offerListArea").add(table);

		tabPanel.setSize("800", "600");
		tabPanel.selectTab(0);
		tabPanel.ensureDebugId("cwTabPanel");

		RootPanel.get().add(tabPanel);

	}

	private void populateDepartureAndArrivalCountries(CountryGWTServiceAsync countryService, final ListBox departureCountries, final ListBox arrivalCountries) {
		AsyncCallback departureCountriesCallback = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// do some UI stuff to show failure
			}

			public void onSuccess(Object result) {

				departureCountries.addItem("Kraj wyjazdu");
				arrivalCountries.addItem("Kraj przyjazdu");

				List<Country> list = (List<Country>) result;
				for (Country country : list) {
					departureCountries.addItem(country.getName(), country.getIso_3166_1_alfa_2());
					arrivalCountries.addItem(country.getName(), country.getIso_3166_1_alfa_2());
				}

			}
		};

		countryService.findAll(departureCountriesCallback);
	}

	private void populateBodies(CarBodyGWTServiceAsync countryService, final ListBox bodies) {
		AsyncCallback departureCountriesCallback = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// do some UI stuff to show failure
			}

			public void onSuccess(Object result) {

				List<CarBody> list = (List<CarBody>) result;
				for (CarBody carBody : list) {
					bodies.addItem(carBody.getName(), carBody.getId().toString());
				}

			}
		};

		countryService.findAll(departureCountriesCallback);
	}

	private void getCargoOffersAndPopulateTable(CargoOfferGWTServiceAsync cargoOfferService, final List<CargoOffer> cargoOffers,
			final CellTable<CargoOffer> table) {

		AsyncCallback allCargoOffersCallback = new AsyncCallback() {

			public void onFailure(Throwable caught) {
				// do some UI stuff to show failure
			}

			public void onSuccess(Object result) {

				List<CargoOffer> list = (List<CargoOffer>) result;

				List<CargoOffer> dataProviderList = dataProvider.getList();

				dataProviderList.clear();

				dataProviderList.addAll(list);

				cargoOffers.addAll(list);

			}
		};

		cargoOfferService.findAllThatAreNotAcceptedNorDeleted(allCargoOffersCallback);
	}

	private CellTable<CargoOffer> createCargoOfferTable() {

		ListHandler<CargoOffer> columnSortHandler = new ListHandler<CargoOffer>(dataProvider.getList());

		final CellTable<CargoOffer> table = new CellTable<CargoOffer>();
		table.setStylePrimaryName("cargoOffersTable");

		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		prepareOfferTypeColumn(columnSortHandler, table);
		prepareFromColumn(columnSortHandler, table);
		prepareToColumn(columnSortHandler, table);
		prepareVehicleColumn(table);
		prepareSubmittedColumn(columnSortHandler, table);
		prepareValidToColumn(columnSortHandler, table);
		prepareContactColumn(table);

		table.addColumnSortHandler(columnSortHandler);

		// Add a selection model to handle user selection.
		final SingleSelectionModel<CargoOffer> selectionModel = new SingleSelectionModel<CargoOffer>();
		table.setSelectionModel(selectionModel);
		selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
			public void onSelectionChange(SelectionChangeEvent event) {
				CargoOffer selected = selectionModel.getSelectedObject();
				if (selected != null) {
					Window.alert("You selected: " + selected.getAddressFrom());
				}
			}
		});

		return table;
	}

	private void prepareContactColumn(final CellTable<CargoOffer> table) {
		final SafeHtmlCell contactCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> contact = new Column<CargoOffer, SafeHtml>(contactCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getContact()).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant("<a href='call'>Zadzwoń</a>").appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant("<a href='write'>Napisz</a>").appendHtmlConstant("</div>");

				return sb.toSafeHtml();

			}
		};
		table.addColumn(contact, "Kontakt");
	}

	private void prepareValidToColumn(ListHandler<CargoOffer> columnSortHandler, final CellTable<CargoOffer> table) {
		final SafeHtmlCell validToCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> validTo = new Column<CargoOffer, SafeHtml>(validToCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				String dzien = DateTimeFormat.getFormat("d-M-yyyy").format(offer.getOfferValid());
				String godzina = DateTimeFormat.getFormat("mm:KK").format(offer.getOfferValid());

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(dzien).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant("godz. ").appendHtmlConstant(godzina)
						.appendHtmlConstant("</div>");
				return sb.toSafeHtml();

			}
		};
		validTo.setSortable(true);
		columnSortHandler.setComparator(validTo, new Comparator<CargoOffer>() {

			public int compare(CargoOffer o1, CargoOffer o2) {

				return (o1.getOfferValid().compareTo(o2.getOfferValid()));

			}
		});
		table.addColumn(validTo, "Ważne do");
	}

	private void prepareSubmittedColumn(ListHandler<CargoOffer> columnSortHandler, final CellTable<CargoOffer> table) {
		final SafeHtmlCell submittedCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> submittedColumn = new Column<CargoOffer, SafeHtml>(submittedCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				String dzien = DateTimeFormat.getFormat("d-M-yyyy").format(offer.getSubmissionDate());
				String godzina = DateTimeFormat.getFormat("mm:KK").format(offer.getSubmissionDate());

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(dzien).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant("godz. ").appendHtmlConstant(godzina)
						.appendHtmlConstant("<div>");

				return sb.toSafeHtml();

			}
		};
		submittedColumn.setSortable(true);
		columnSortHandler.setComparator(submittedColumn, new Comparator<CargoOffer>() {

			public int compare(CargoOffer o1, CargoOffer o2) {

				return (o1.getSubmissionDate().compareTo(o2.getSubmissionDate()));

			}
		});
		table.addColumn(submittedColumn, "Zgłoszono");
	}

	private void prepareVehicleColumn(final CellTable<CargoOffer> table) {
		final SafeHtmlCell vehicleCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> vehicleColumn = new Column<CargoOffer, SafeHtml>(vehicleCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();

				sb.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getVolume()).appendHtmlConstant("<div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getBody()).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getCargoLength()).appendHtmlConstant("</div>");
				return sb.toSafeHtml();

			}
		};
		vehicleColumn.setSortable(true);
		table.addColumn(vehicleColumn, "Pojazd");
	}

	private void prepareToColumn(ListHandler<CargoOffer> columnSortHandler, final CellTable<CargoOffer> table) {
		final SafeHtmlCell toCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> toColumn = new Column<CargoOffer, SafeHtml>(toCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant("<img src='images/blucargo/countries/" + offer.getCountryTo().toUpperCase() + ".png' width='16' height='16'/></br>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getCountryTo()).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getCityTo()).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getPostOfficeTo()).appendHtmlConstant("<div>");
				return sb.toSafeHtml();

			}
		};
		toColumn.setSortable(true);

		columnSortHandler.setComparator(toColumn, new Comparator<CargoOffer>() {

			public int compare(CargoOffer o1, CargoOffer o2) {

				return (o1.getCountryTo().compareTo(o2.getCountryTo()));

			}
		});

		table.addColumn(toColumn, "Rozładunek");
	}

	private void prepareFromColumn(ListHandler<CargoOffer> columnSortHandler, final CellTable<CargoOffer> table) {
		final SafeHtmlCell fromCell = new SafeHtmlCell();

		Column<CargoOffer, SafeHtml> fromColumn = new Column<CargoOffer, SafeHtml>(fromCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant("<img src='images/blucargo/countries/" + offer.getCountryFrom().toUpperCase() + ".png' width='16' height='16'/></br>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getCountryFrom()).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getCityFrom()).appendHtmlConstant("</div>")
						.appendHtmlConstant("<div class='offerDescription'>").appendHtmlConstant(offer.getPostOfficeFrom()).appendHtmlConstant("</div>");
				return sb.toSafeHtml();

			}
		};
		fromColumn.setSortable(true);
		columnSortHandler.setComparator(fromColumn, new Comparator<CargoOffer>() {

			public int compare(CargoOffer o1, CargoOffer o2) {

				return (o1.getCountryFrom().compareTo(o2.getCountryFrom()));

			}
		});

		table.addColumn(fromColumn, "Załadunek");
	}

	private void prepareOfferTypeColumn(ListHandler<CargoOffer> columnSortHandler, final CellTable<CargoOffer> table) {
		final SafeHtmlCell offerTypeCell = new SafeHtmlCell();

		Column<CargoOffer, SafeHtml> offerType = new Column<CargoOffer, SafeHtml>(offerTypeCell) {
			@Override
			public SafeHtml getValue(CargoOffer object) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();

				if (OfferType.CARGO == object.getType()) {
					sb.appendHtmlConstant("<img src='images/blucargo/ladunek_ico.png' width='16' height='20'/></br>");
					sb.appendHtmlConstant("<div class='offerDescription'>Wolny ładunek</div>");
					sb.appendHtmlConstant("<div class='offerAction'>Dodaj do ulubionych<div>");
				} else if (OfferType.VEHICLE == object.getType()) {
					sb.appendHtmlConstant("<img src='images/blucargo/pojazd_ico.png' width='16' height='20'/></br>");
					sb.appendHtmlConstant("<div class='offerDescription'>Wolny pojazd</div>");
					sb.appendHtmlConstant("<div class='offerAction'>Dodaj do ulubionych</div>");
				}

				return sb.toSafeHtml();

			}
		};

		offerType.setSortable(true);

		columnSortHandler.setComparator(offerType, new Comparator<CargoOffer>() {

			public int compare(CargoOffer o1, CargoOffer o2) {

				return o1.getType().compareTo(o2.getType());

			}
		});

		table.addColumn(offerType, "Rodzaj ogłoszenia");
	}
}
