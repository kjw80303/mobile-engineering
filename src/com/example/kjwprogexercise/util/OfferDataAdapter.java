package com.example.kjwprogexercise.util;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kjwprogexercise.R;
import com.example.kjwprogexercise.model.Offer;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class OfferDataAdapter extends BaseAdapter {

	private List<Offer> offers;
	DisplayImageOptions options;

	public OfferDataAdapter(List<Offer> offers) {
		
		options = new DisplayImageOptions.Builder()
	    .displayer(new RoundedBitmapDisplayer(10))
	    .build();
		
		this.setOffers(offers);
	}

	@Override
	public int getCount() {
		return getOffers().size();
	}

	@Override
	public Object getItem(int position) {
		return getOffers().get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.offer_item_view, parent, false);
		}

		Offer offer = getOffers().get(position);

		ImageView offerImage = (ImageView) convertView.findViewById(R.id.offerImage);
		ImageLoader.getInstance().displayImage(offer.src, offerImage, options);

		TextView mainText = (TextView) convertView.findViewById(R.id.mainText);
		mainText.setText(offer.attrib);

		TextView subText = (TextView) convertView.findViewById(R.id.subText);
		subText.setText(offer.desc);

		ImageView avatarImage = (ImageView) convertView.findViewById(R.id.avatarImage);
		ImageLoader.getInstance().displayImage(offer.user.avatar.src, avatarImage);

		TextView username = (TextView) convertView.findViewById(R.id.username);
		username.setText(offer.user.username);

		return convertView;
	}

	public List<Offer> getOffers() {
		return offers;
	}

	public void setOffers(List<Offer> offers) {
		this.offers = offers;
	}

}
