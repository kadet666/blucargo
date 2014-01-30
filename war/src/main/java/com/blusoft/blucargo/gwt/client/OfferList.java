package com.blusoft.blucargo.gwt.client;

import java.util.ArrayList;
import java.util.List;

import com.blusoft.blucargo.model.CargoOffer;
import com.blusoft.blucargo.model.OfferType;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy.KeyboardSelectionPolicy;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;

public class OfferList implements EntryPoint {

	public void onModuleLoad() {

		CargoOfferGWTServiceAsync cargoOfferService = (CargoOfferGWTServiceAsync) GWT.create(CargoOfferGWTService.class);

		final List<CargoOffer> cargoOffers = new ArrayList<CargoOffer>();

		final CellTable<CargoOffer> table = createCargoOfferTable();

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
				cargoOffers.clear();
				cargoOffers.addAll(list);

				// Set the total row count. This isn't strictly necessary, but
				// it
				// affects
				// paging calculations, so its good habit to keep the row count
				// up to
				// date.
				table.setRowCount(cargoOffers.size(), true);

				// Push the data into the widget.
				table.setRowData(0, cargoOffers);

			}
		};

		cargoOfferService.findAllThatAreNotAcceptedNorDeleted(allCargoOffersCallback);
	}

	private CellTable<CargoOffer> createCargoOfferTable() {
		final CellTable<CargoOffer> table = new CellTable<CargoOffer>();

		table.setKeyboardSelectionPolicy(KeyboardSelectionPolicy.ENABLED);

		TextColumn<CargoOffer> offerType = new TextColumn<CargoOffer>() {
			@Override
			public String getValue(CargoOffer object) {
				if (OfferType.CARGO == object.getType()) {
					return "Ładunek";
				} else if (OfferType.VEHICLE == object.getType()) {
					return "Pojazd";
				}
				return null;
			}
		};
		table.addColumn(offerType, "Rodzaj ogłoszenia");

		final SafeHtmlCell fromCell = new SafeHtmlCell();

		Column<CargoOffer, SafeHtml> fromColumn = new Column<CargoOffer, SafeHtml>(fromCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant(offer.getCountryFrom()).appendHtmlConstant("<br>").appendHtmlConstant(offer.getCityFrom())
						.appendHtmlConstant(offer.getPostOfficeFrom());
				return sb.toSafeHtml();

			}
		};
		table.addColumn(fromColumn, "Załadunek");

		final SafeHtmlCell toCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> toColumn = new Column<CargoOffer, SafeHtml>(toCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();

				sb.appendHtmlConstant(offer.getCountryTo()).appendHtmlConstant("<br>").appendHtmlConstant(offer.getCityTo())
						.appendHtmlConstant(offer.getPostOfficeTo());
				return sb.toSafeHtml();

			}
		};
		table.addColumn(toColumn, "Rozładunek");

		final SafeHtmlCell vehicleCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> vehicle = new Column<CargoOffer, SafeHtml>(vehicleCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant(offer.getVolume()).appendHtmlConstant("<br>").appendHtmlConstant(offer.getBody())
						.appendHtmlConstant(offer.getCargoLength());
				return sb.toSafeHtml();

			}
		};
		table.addColumn(toColumn, "Pojazd");

		final SafeHtmlCell countryFromCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> countryFromColumn = new Column<CargoOffer, SafeHtml>(countryFromCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				String dzien = DateTimeFormat.getFormat("d-M-yyyy").format(offer.getSubmissionDate());
				String godzina = DateTimeFormat.getFormat("mm:KK").format(offer.getSubmissionDate());

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant(dzien).appendHtmlConstant("\n godz").appendHtmlConstant(godzina);
				return sb.toSafeHtml();

			}
		};
		table.addColumn(countryFromColumn, "Zgłoszono");

		final SafeHtmlCell validToCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> validTo = new Column<CargoOffer, SafeHtml>(validToCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				String dzien = DateTimeFormat.getFormat("d-M-yyyy").format(offer.getOfferValid());
				String godzina = DateTimeFormat.getFormat("mm:KK").format(offer.getOfferValid());

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant(dzien).appendHtmlConstant("<br> godz").appendHtmlConstant(godzina);
				return sb.toSafeHtml();

			}
		};
		table.addColumn(validTo, "Ważne do");

		final SafeHtmlCell contactCell = new SafeHtmlCell();
		Column<CargoOffer, SafeHtml> contact = new Column<CargoOffer, SafeHtml>(contactCell) {
			@Override
			public SafeHtml getValue(CargoOffer offer) {

				SafeHtmlBuilder sb = new SafeHtmlBuilder();
				sb.appendHtmlConstant(offer.getContact());

				return sb.toSafeHtml();

			}
		};
		table.addColumn(contact, "Kontakt");

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
}
