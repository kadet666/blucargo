package com.blusoft.blucargo.gwt.client;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.OfferType;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class OfferList implements EntryPoint {

	ListDataProvider<CargoOffer> dataProvider = new ListDataProvider<CargoOffer>();

	public void onModuleLoad() {

		CargoOfferGWTServiceAsync cargoOfferService = (CargoOfferGWTServiceAsync) GWT.create(CargoOfferGWTService.class);

		final List<CargoOffer> cargoOffers = new ArrayList<CargoOffer>();

		final CellTable<CargoOffer> table = createCargoOfferTable();

		dataProvider.addDataDisplay(table);

		getCargoOffersAndPopulateTable(cargoOfferService, cargoOffers, table);

		RootPanel.get("offerListArea").add(table);

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
				sb.appendHtmlConstant(offer.getContact()).appendHtmlConstant("<br>").appendHtmlConstant("<a href='call'>Zadzwoń</a>")
						.appendHtmlConstant("<br>").appendHtmlConstant("<a href='write'>Napisz</a>").appendHtmlConstant("<br>");

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
				sb.appendHtmlConstant(dzien).appendHtmlConstant("<br>").appendHtmlConstant("godz. ").appendHtmlConstant(godzina);
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
				sb.appendHtmlConstant(dzien).appendHtmlConstant("<br>").appendHtmlConstant("godz. ").appendHtmlConstant(godzina);

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

				sb.appendHtmlConstant(offer.getVolume()).appendHtmlConstant("<br>").appendHtmlConstant(offer.getBody())
						.appendHtmlConstant(offer.getCargoLength());
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
						.appendHtmlConstant(offer.getCountryTo()).appendHtmlConstant("</br>").appendHtmlConstant(offer.getCityTo()).appendHtmlConstant("</br>")
						.appendHtmlConstant(offer.getPostOfficeTo());
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
						.appendHtmlConstant(offer.getCountryFrom()).appendHtmlConstant("<br>").appendHtmlConstant(offer.getCityFrom())
						.appendHtmlConstant("<br>").appendHtmlConstant(offer.getPostOfficeFrom());
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
					sb.appendHtmlConstant("Wolny ładunek</br>");
					sb.appendHtmlConstant("Dodaj do ulubionych");
				} else if (OfferType.VEHICLE == object.getType()) {
					sb.appendHtmlConstant("<img src='images/blucargo/pojazd_ico.png' width='16' height='20'/></br>");
					sb.appendHtmlConstant("Wolny pojazd</br>");
					sb.appendHtmlConstant("Dodaj do ulubionych");
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
