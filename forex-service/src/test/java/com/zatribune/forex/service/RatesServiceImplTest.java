package com.zatribune.forex.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zatribune.forex.db.entity.TradeRate;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertFalse;

class RatesServiceImplTest {


    @Test
    void testConversion() throws IOException {
        ObjectMapper objectMapper=new ObjectMapper();
        String input="{\"motd\":{\"msg\":\"If you or your company use this project or like what we doing, please consider backing us so we can continue maintaining and evolving this project.\",\"url\":\"https://exchangerate.host/#/donate\"},\"success\":true,\"base\":\"EUR\",\"date\":\"2023-08-18\",\"rates\":{\"AED\":3.996174,\"AFN\":92.313672,\"ALL\":105.429807,\"AMD\":420.452394,\"ANG\":1.960897,\"AOA\":899.603655,\"ARS\":380.410327,\"AUD\":1.700709,\"AWG\":1.961174,\"AZN\":1.849631,\"BAM\":1.954818,\"BBD\":2.17618,\"BDT\":119.037923,\"BGN\":1.959836,\"BHD\":0.409975,\"BIF\":3082.56625,\"BMD\":1.088184,\"BND\":1.478509,\"BOB\":7.516349,\"BRL\":5.414531,\"BSD\":1.08808,\"BTC\":4.1E-5,\"BTN\":90.411697,\"BWP\":14.798719,\"BYN\":2.745513,\"BZD\":2.192684,\"CAD\":1.475235,\"CDF\":2692.306558,\"CHF\":0.956091,\"CLF\":0.034891,\"CLP\":940.92854,\"CNH\":7.947209,\"CNY\":7.924889,\"COP\":4500.116422,\"CRC\":581.313799,\"CUC\":1.087839,\"CUP\":28.010992,\"CVE\":110.399127,\"CZK\":24.075162,\"DJF\":193.586359,\"DKK\":7.450576,\"DOP\":61.672221,\"DZD\":147.889673,\"EGP\":33.583994,\"ERN\":16.317124,\"ETB\":59.763075,\"EUR\":1,\"FJD\":2.471074,\"FKP\":0.85431,\"GBP\":0.854571,\"GEL\":2.850814,\"GGP\":0.854684,\"GHS\":12.292148,\"GIP\":0.854564,\"GMD\":65.866593,\"GNF\":9372.657579,\"GTQ\":8.539174,\"GYD\":227.549382,\"HKD\":8.516358,\"HNL\":26.76964,\"HRK\":7.532584,\"HTG\":147.373641,\"HUF\":385.132408,\"IDR\":16644.350028,\"ILS\":4.119521,\"IMP\":0.854638,\"INR\":90.39202,\"IQD\":1424.212317,\"IRR\":45986.378676,\"ISK\":143.665344,\"JEP\":0.854058,\"JMD\":168.238243,\"JOD\":0.770608,\"JPY\":158.164787,\"KES\":156.914409,\"KGS\":96.019714,\"KHR\":4498.034043,\"KMF\":492.22515,\"KPW\":979.012884,\"KRW\":1455.878045,\"KWD\":0.335864,\"KYD\":0.907533,\"KZT\":502.755875,\"LAK\":21118.610701,\"LBP\":16403.548023,\"LKR\":351.311051,\"LRD\":202.601761,\"LSL\":20.776034,\"LYD\":5.241224,\"MAD\":10.786995,\"MDL\":19.235612,\"MGA\":4897.041773,\"MKD\":61.567469,\"MMK\":2283.978808,\"MNT\":3752.880994,\"MOP\":8.770458,\"MRU\":41.242119,\"MUR\":49.440221,\"MVR\":16.725075,\"MWK\":1177.464071,\"MXN\":18.596149,\"MYR\":5.051239,\"MZN\":69.483156,\"NAD\":20.788098,\"NGN\":835.391797,\"NIO\":39.784234,\"NOK\":11.508945,\"NPR\":144.659007,\"NZD\":1.83733,\"OMR\":0.419787,\"PAB\":1.088409,\"PEN\":4.052427,\"PGK\":3.912038,\"PHP\":61.42374,\"PKR\":321.8495,\"PLN\":4.474754,\"PYG\":7904.731142,\"QAR\":3.961454,\"RON\":4.944596,\"RSD\":117.283628,\"RUB\":101.556932,\"RWF\":1287.831457,\"SAR\":4.079978,\"SBD\":9.104542,\"SCR\":14.741161,\"SDG\":653.76354,\"SEK\":11.890228,\"SGD\":1.47715,\"SHP\":0.853991,\"SLL\":22810.440766,\"SOS\":618.996464,\"SRD\":41.557683,\"SSP\":141.695899,\"STD\":24237.94934,\"STN\":24.757826,\"SVC\":9.516702,\"SYP\":2733.109298,\"SZL\":20.760807,\"THB\":38.533439,\"TJS\":11.947935,\"TMT\":3.807147,\"TND\":3.360506,\"TOP\":2.601157,\"TRY\":29.483537,\"TTD\":7.374867,\"TWD\":34.669588,\"TZS\":2724.917799,\"UAH\":40.16853,\"UGX\":4060.183899,\"USD\":1.088186,\"UYU\":41.283346,\"UZS\":13127.97584,\"VES\":34.345277,\"VND\":25884.396495,\"VUV\":129.145615,\"WST\":2.957379,\"XAF\":655.75487,\"XAG\":0.048251,\"XAU\":0.001101,\"XCD\":2.940575,\"XDR\":0.816356,\"XOF\":655.755359,\"XPD\":0.001904,\"XPF\":119.295358,\"XPT\":0.001886,\"YER\":272.329047,\"ZAR\":20.754418,\"ZMW\":21.127213,\"ZWL\":350.269309}}";
        ObjectNode node=objectMapper.readValue(input,ObjectNode.class);
        Map<String, Double> map = objectMapper.readValue(node.get("rates").traverse(), new TypeReference<>() {});
        List<TradeRate> list=map.entrySet().stream().map(entry->TradeRate.builder()
                .source("EUR")
                .destination(entry.getKey())
                .rate(entry.getValue())
                .build()).collect(Collectors.toList());
        assertFalse(list.isEmpty());
    }

}