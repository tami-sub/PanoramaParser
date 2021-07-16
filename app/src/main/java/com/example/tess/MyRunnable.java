package com.example.tess;

import android.widget.ImageView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MyRunnable implements Runnable {
    ImageView[] image;
    TextView[] text;

    public MyRunnable(ImageView[] image, TextView[] text) {
        this.image = image;
        this.text = text;
    }

    @Override
    public void run() {
        try {
            getWeb();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getWeb() throws IOException {

        Document doc = Jsoup.connect("https://panorama.pub/").get();
        Elements elem = doc.getElementsByClass("news small-previews");
        Element our_element = elem.get(0);
        Elements components = our_element.children();

        for (int i = 0; i < components.size(); i++) {
            Elements comp = components.get(i).children();
            String attr = comp.get(0).toString();

            System.out.println(attr);

            String url = doc.baseUri() + attr.substring(attr.indexOf("storage"), attr.indexOf("')"));
            System.out.println(url);

            Element title_elem = comp.get(1);
            String title_elements = title_elem.children().toString();
            String title = title_elements.substring(4, title_elements.indexOf("</h3>"));
            System.out.println(title);
            if (i < 10) {
                new ImageLoadTask(url, image[i]).execute();
                text[i].setText(title);
            }
        }
    }


}
