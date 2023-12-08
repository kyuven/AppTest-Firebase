package com.example.appfood_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainMenu extends AppCompatActivity {

    SearchView searchView;
    public ArrayList<Product> productList;
    ProductAdapter productAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        searchView = findViewById(R.id.search_view);

        recyclerView = findViewById(R.id.recycler_itens);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Cria os Itens no RecyclerView - Implementar em array para melhor organização
        productList = new ArrayList<Product>();
        productList.add(new Product("Rice", "4.5", "Rice is the seed of the grass species Oryza sativa (Asian rice) or, less commonly, O. glaberrima (African rice). The name wild rice is usually used for species of the genera Zizania and Porteresia, both wild and domesticated, although the term may also be used for primitive or uncultivated varieties of Oryza." +
                "\n", "14.00", R.drawable.arroz));
        productList.add(new Product("Guioza", "3.2", "Jiaozi (Chinese: 餃子; [tɕjàʊ.tsɨ] ⓘ) are a type of Chinese dumpling. Jiaozi are folded to resemble Chinese sycee and have great cultural significance attached to them within China. Jiaozi are one of the major dishes eaten during the Chinese New Year throughout northern China and eaten all year round in the northern provinces. Though considered part of Chinese cuisine, jiaozi are popular in other parts of East Asia and in the Western world, where a fried variety is referred to as potstickers. The English-language term \"potsticker\" was invented by Buwei Yang Chao in her book How to Cook and Eat in Chinese (1945)." +
                "\n", "24.00", R.drawable.guioza));
        productList.add(new Product("Temaki", "5.0", "Tekkamaki (鉄火巻) is a kind of hosomaki filled with raw tuna. Although it is believed that the word tekka, meaning \"red hot iron\", alludes to the color of the tuna flesh or salmon flesh, it actually originated as a quick snack to eat in gambling dens called tekkaba (鉄火場), much like the origins of the sandwich" +
                "\n",  "08.00", R.drawable.temaki));
        productList.add(new Product("Shimeji", "4.5", "Shimeji mushrooms are a variety of Asian mushrooms with a crunchy texture and nutty, savory flavor. They're also known as beech mushrooms, due to the fact that in the wild, they grow on fallen beech trees. " +
                "\n", "14.00", R.drawable.shimeji));
        productList.add(new Product("Hot Roll", "3.8", "O hot roll (ou simplesmente hot) ou sushi empanado é um alimento abrasileirado da culinária japonesa, do tipo maki (\"sushi enrolado\"), feito com peixe, normalmente salmão ou atum, enrolados com arroz em nori, uma alga marinha" +
                "\n", "15.00",  R.drawable.hot_roll));
        productList.add(new Product("Lamen", "4.9", "Ramen (/ˈrɑːmən/) (拉麺, ラーメン or らーめん, rāmen, [ɾaꜜːmeɴ] ⓘ) is a Japanese noodle dish. It consists of Chinese-style wheat noodles (or 中華麺, chūkamen) served in a broth; common flavors are soy sauce and miso, with typical toppings including sliced pork (chāshū), nori (dried seaweed), menma (bamboo shoots), and scallions. Ramen has its roots in Chinese noodle dishes.[1] Nearly every region in Japan has its own variation of ramen, such as the tonkotsu (pork bone broth) ramen of Kyushu and the miso ramen of Hokkaido." +
                "\n", "20.00", R.drawable.lamen));
        productList.add(new Product("Onigiri", "4.9", "Onigiri (お握り or 御握り), also known as omusubi (お結び), nigirimeshi (握り飯), or rice ball, is a Japanese food made from white rice formed into triangular or cylindrical shapes and often wrapped in nori." +
                "\n", "25.00",  R.drawable.onigiri));
        productList.add(new Product("Sashimi", "4.7", "Sashimi (刺身, English: /səˈʃiːmi/ sə-SHEE-mee, Japanese: [saɕimiꜜ]) is a Japanese delicacy consisting of fresh raw fish or meat sliced into thin pieces and often eaten with soy sauce.[1]" +
                "\n", "14.00", R.drawable.sashimi));
        productList.add(new Product("Missoshiru", "4.5", "Miso soup (味噌汁, misoshiru) is a traditional Japanese soup consisting of a dashi stock into which softened miso paste is mixed. In addition, there are many optional ingredients (various vegetables, tofu, abura-age, etc.) that may be added depending on regional and seasonal recipes, and personal preference. In Japanese food culture, miso soup is a representative of soup dishes served with rice. Miso soup is also called omiotsuke (御味御付)." +
                "\n", "18.00",  R.drawable.missoshiru));

        productAdapter = new ProductAdapter(this, productList);
        recyclerView.setAdapter(productAdapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText);
                return false;
            }
        });
    }

    private void filter(String newText) {

        List<Product> filteredList = new ArrayList<>();
        for(Product item : productList) {

            // if wants to filter to start with the design letter, change contains to startsWith
            if(item.getName().toLowerCase().contains(newText.toLowerCase())) {
                filteredList.add(item);
            }
        }

        productAdapter.filteredList(filteredList);

    }
}