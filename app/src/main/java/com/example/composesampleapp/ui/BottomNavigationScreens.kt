package com.example.composesampleapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composesampleapp.ui.BooksModel
import com.example.composesampleapp.ui.NewsModel
import kotlinx.coroutines.launch

@Composable
fun BookScreen(books: ArrayList<BooksModel> = getBooksModel(), scaffoldState: ScaffoldState) {
    val scope = rememberCoroutineScope()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_medium)),
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.dimen_extra_extra_large))
            .fillMaxSize()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(books) { book ->
            Card(
                shape = RoundedCornerShape(8),
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(id = R.dimen.dimen_small),
                        top = dimensionResource(id = R.dimen.dimen_extra_small),
                        start = dimensionResource(id = R.dimen.dimen_small),
                        end = dimensionResource(id = R.dimen.dimen_small)
                    )
                    .fillMaxWidth(),
                elevation = dimensionResource(id = R.dimen.dimen_small)
            ) {
                Row(
                    modifier = Modifier
                        .clickable {
                            scope.launch {
                                scaffoldState.snackbarHostState.showSnackbar(
                                    "${book.name} tıkladınız",
                                    "Tamam"
                                )
                            }
                        }
                        .fillMaxWidth()
                        .padding(
                            end = dimensionResource(id = R.dimen.dimen_medium),
                            top = dimensionResource(id = R.dimen.dimen_extra_small),
                            bottom = dimensionResource(id = R.dimen.dimen_extra_small)
                        ),
                    verticalAlignment = Alignment.CenterVertically

                ) {
                    Image(
                        painterResource(book.image),
                        "Book Image",
                        modifier = Modifier
                            .width(100.dp)
                            .height(100.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.dimen_large),
                                end = dimensionResource(id = R.dimen.dimen_small),
                                bottom = dimensionResource(id = R.dimen.dimen_small),
                                top = dimensionResource(id = R.dimen.dimen_extra_small)
                            )
                    )
                    {
                        Text(
                            text = book.name,
                            modifier = Modifier.fillMaxWidth(),
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            fontSize = dimensionResource(id = R.dimen.dimen_extra_large).value.sp
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_extra_small)))
                        Text(
                            text = book.writer,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Black,
                            fontSize = dimensionResource(id = R.dimen.dimen_large).value.sp
                        )
                        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_extra_small)))
                        Text(
                            text = book.publisher,
                            modifier = Modifier.fillMaxWidth(),
                            color = Color.Gray,
                            fontSize = dimensionResource(id = R.dimen.dimen_medium).value.sp
                        )
                    }
                }
            }
        }
    }
}

fun getBooksModel() = arrayListOf(
    BooksModel(R.drawable.ic_jack_london_book, "Martin Eden", "Türkiye İş Bankası", "Jack London"),
    BooksModel(
        R.drawable.ic_ihsan_anar_book,
        "Puslu Kıtalar Atlası",
        "İletişim Yayınları",
        "İhsan Oktay Anar"
    ),
    BooksModel(
        R.drawable.ic_ernst_hemingway_book,
        "Yaşlı Adam ve Deniz",
        "Bilgi Yayınevi",
        "Ernst Hemingway"
    ),
    BooksModel(
        R.drawable.ic_agatha_christie_book,
        "On Küçük Zenci",
        "Altın Kitaplar",
        "Agatha Christie"
    ),
    BooksModel(
        R.drawable.ic_baris_bicakci_book,
        "Bizim Büyük Çaresizliğimiz",
        "İletişim Yayınları",
        "Barış Bıçakçı"
    ),
    BooksModel(
        R.drawable.ic_beyhan_budak_book,
        "Mutluluğu Kaybettiğin Yerde Arama",
        "Sahi Kitap",
        "Beyhan Budak"
    ),
    BooksModel(R.drawable.ic_kafka_book, "Dönüşüm", "Türkiye İş Bankası", "Franz Kafka"),
    BooksModel(
        R.drawable.ic_peyami_safa_book,
        "Dokuzuncu Hariciye Koğuşu",
        "Ötüken Neşriyat",
        "Peyami Safa"
    )
)

@Composable
fun NewsScreen(news: ArrayList<NewsModel> = getNewsModel()) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(id = R.dimen.dimen_medium)),
        modifier = Modifier
            .padding(top = dimensionResource(id = R.dimen.dimen_extra_extra_large))
            .fillMaxSize()
            .fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        items(news) { new ->
            Card(
                shape = RoundedCornerShape(4),
                modifier = Modifier
                    .padding(
                        bottom = dimensionResource(id = R.dimen.dimen_small),
                        top = dimensionResource(id = R.dimen.dimen_extra_small),
                        start = dimensionResource(id = R.dimen.dimen_small),
                        end = dimensionResource(id = R.dimen.dimen_small)
                    )
                    .fillMaxWidth(),
                elevation = dimensionResource(id = R.dimen.dimen_small)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Image(
                        painterResource(new.image),
                        "News Image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_extra_small)))
                    Text(
                        text = new.title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.dimen_small),
                                end = dimensionResource(id = R.dimen.dimen_small)
                            ),
                        color = Color.Black,
                        fontSize = dimensionResource(id = R.dimen.dimen_large).value.sp
                    )
                    Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.dimen_extra_small)))
                    Text(
                        text = new.description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = dimensionResource(id = R.dimen.dimen_small),
                                end = dimensionResource(id = R.dimen.dimen_small),
                                bottom = dimensionResource(id = R.dimen.dimen_small)
                            ),
                        color = Color.Gray,
                        fontSize = dimensionResource(id = R.dimen.dimen_medium).value.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

            }
        }

    }
}

fun getNewsModel() = arrayListOf(
    NewsModel(
        R.drawable.ic_news_one,
        "Erzincan'da eğitime kar engeli",
        "Erzincan'da olumsuz hava şartları sebebiyle il genelinde tüm okullar 3 Ocak Pazartesi günü tatil edildi."
    ),
    NewsModel(
        R.drawable.ic_news_two,
        "Liverpool talip oldu! 21 milyon sterlin...",
        "Fenerbahçe'de gösterdiği performansla birçok Avrupa devinin listesine giren Attila Szalai'nin Liverpool'un da gündeminde olduğu iddia edildi."
    ),
    NewsModel(
        R.drawable.ic_news_three,
        "Yabancı gelin Sonia'ydı... Yeşilçam'ın yıldızı oldu.",
        "Suna Yıldızoğlu, 1974 yılında İngiltere’den Türkiye’ye gelerek tüm hayatını değiştirdi."
    ),
    NewsModel(
        R.drawable.ic_news_four,
        "9 milyon turist Antalya'yı seçti",
        "Dünyanın en önemli turizm destinasyonlarından Antalya, 2020’deki büyük kaybın ardından 2021’de toparlanma sürecine girdi."
    ),
    NewsModel(
        R.drawable.ic_news_five,
        "Ediz Hun'u hiç böyle dinlemediniz!",
        "2 yıl önce Agatha Christie'nin ölümsüz eserinden uyarlanan ‘’On Kişiydiler’’ oyunuyla ilk kez tiyatro sahnesine çıkan Ediz Hun, galasının ardından 'salgın yüzünden' sahnelenemeyen oyunuyla yeniden buluştu."
    ),
    NewsModel(
        R.drawable.ic_news_six,
        "5G korkusu! Milyonlar etkilenecek",
        "ABD'de yeni yılın ilk haftası devreye girmesi planlanan 5G teknolojisi, havacılık ile iletişim sektörlerinin arasını açtı."
    ),
    NewsModel(
        R.drawable.ic_news_seven,
        "Hükümetten uyarı: Çalışanların yüzde 25'i hastalanabilir",
        "Omicron varyantının yayıldığı ve vaka sayılarında her gün rekor kırılan İngiltere'de hükümetten işyerlerine uyarı yapıldı."
    ),
    NewsModel(
        R.drawable.ic_news_eight,
        "Farklı bir 2021 almanağı... İklim krizi yılı",
        "Salgın hastalıklar ve birbiri ardına gelen doğal afetler nedeniyle oldukça zor bir yıl geçirdik."
    )
)

