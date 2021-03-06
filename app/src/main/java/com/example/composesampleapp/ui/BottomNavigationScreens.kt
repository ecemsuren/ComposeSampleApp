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
                                    "${book.name} t??klad??n??z",
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
    BooksModel(R.drawable.ic_jack_london_book, "Martin Eden", "T??rkiye ???? Bankas??", "Jack London"),
    BooksModel(
        R.drawable.ic_ihsan_anar_book,
        "Puslu K??talar Atlas??",
        "??leti??im Yay??nlar??",
        "??hsan Oktay Anar"
    ),
    BooksModel(
        R.drawable.ic_ernst_hemingway_book,
        "Ya??l?? Adam ve Deniz",
        "Bilgi Yay??nevi",
        "Ernst Hemingway"
    ),
    BooksModel(
        R.drawable.ic_agatha_christie_book,
        "On K??????k Zenci",
        "Alt??n Kitaplar",
        "Agatha Christie"
    ),
    BooksModel(
        R.drawable.ic_baris_bicakci_book,
        "Bizim B??y??k ??aresizli??imiz",
        "??leti??im Yay??nlar??",
        "Bar???? B????ak????"
    ),
    BooksModel(
        R.drawable.ic_beyhan_budak_book,
        "Mutlulu??u Kaybetti??in Yerde Arama",
        "Sahi Kitap",
        "Beyhan Budak"
    ),
    BooksModel(R.drawable.ic_kafka_book, "D??n??????m", "T??rkiye ???? Bankas??", "Franz Kafka"),
    BooksModel(
        R.drawable.ic_peyami_safa_book,
        "Dokuzuncu Hariciye Ko??u??u",
        "??t??ken Ne??riyat",
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
        "Erzincan'da e??itime kar engeli",
        "Erzincan'da olumsuz hava ??artlar?? sebebiyle il genelinde t??m okullar 3 Ocak Pazartesi g??n?? tatil edildi."
    ),
    NewsModel(
        R.drawable.ic_news_two,
        "Liverpool talip oldu! 21 milyon sterlin...",
        "Fenerbah??e'de g??sterdi??i performansla bir??ok Avrupa devinin listesine giren Attila Szalai'nin Liverpool'un da g??ndeminde oldu??u iddia edildi."
    ),
    NewsModel(
        R.drawable.ic_news_three,
        "Yabanc?? gelin Sonia'yd??... Ye??il??am'??n y??ld??z?? oldu.",
        "Suna Y??ld??zo??lu, 1974 y??l??nda ??ngiltere???den T??rkiye???ye gelerek t??m hayat??n?? de??i??tirdi."
    ),
    NewsModel(
        R.drawable.ic_news_four,
        "9 milyon turist Antalya'y?? se??ti",
        "D??nyan??n en ??nemli turizm destinasyonlar??ndan Antalya, 2020???deki b??y??k kayb??n ard??ndan 2021???de toparlanma s??recine girdi."
    ),
    NewsModel(
        R.drawable.ic_news_five,
        "Ediz Hun'u hi?? b??yle dinlemediniz!",
        "2 y??l ??nce Agatha Christie'nin ??l??ms??z eserinden uyarlanan ??????On Ki??iydiler?????? oyunuyla ilk kez tiyatro sahnesine ????kan Ediz Hun, galas??n??n ard??ndan 'salg??n y??z??nden' sahnelenemeyen oyunuyla yeniden bulu??tu."
    ),
    NewsModel(
        R.drawable.ic_news_six,
        "5G korkusu! Milyonlar etkilenecek",
        "ABD'de yeni y??l??n ilk haftas?? devreye girmesi planlanan 5G teknolojisi, havac??l??k ile ileti??im sekt??rlerinin aras??n?? a??t??."
    ),
    NewsModel(
        R.drawable.ic_news_seven,
        "H??k??metten uyar??: ??al????anlar??n y??zde 25'i hastalanabilir",
        "Omicron varyant??n??n yay??ld?????? ve vaka say??lar??nda her g??n rekor k??r??lan ??ngiltere'de h??k??metten i??yerlerine uyar?? yap??ld??."
    ),
    NewsModel(
        R.drawable.ic_news_eight,
        "Farkl?? bir 2021 almana????... ??klim krizi y??l??",
        "Salg??n hastal??klar ve birbiri ard??na gelen do??al afetler nedeniyle olduk??a zor bir y??l ge??irdik."
    )
)

