package com.hyunjung.munuhmunhwa

import org.json.JSONException
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.RuntimeException
import java.lang.StringBuilder
import java.net.MalformedURLException
import java.net.URL

class ContentSearch{
    var contentItems = ArrayList<ContentItem>()
    var adapter = ContentAdapter(contentItems)

    val queryurl = "http://openapi.seoul.go.kr:8088/4455464864646e66363045525a4766/json/culturalEventInfo/1/63/"

    fun main(): ContentAdapter{
        // URL 연결해오기
        val responseBody = get(queryurl)
        // 데이터 파싱하기
        parseData(responseBody)
        return adapter
    }

    // url 연결하기
    private operator fun get(apiUrl: String): String{
        var responseBody: String =""
        try{
            val url = URL(apiUrl)
            val `in` = url.openStream()
            responseBody = readBody(`in`)
        }catch (e: MalformedURLException){
            e.printStackTrace()
        }
        return responseBody
    }

    // 데이터 응답 받기
    private fun readBody(body: InputStream): String{
        val streamReader = InputStreamReader(body)
        try{
            BufferedReader(streamReader).use({ lineReader->
                val responseBody = StringBuilder()
                var line: String? = lineReader.readLine()

                while(line!=null){
                    responseBody.append(line)
                    line = lineReader.readLine()
                }
                return responseBody.toString()
            })
        }catch (e: IOException){
            throw RuntimeException("API 응답을 읽는 데 실패했습니다. ", e)
        }
    }

    // 데이터 파싱해서 넣기
    private fun parseData(responseBody: String){
        var title: String
        var date: String
        var image: String
        var jsonObject: JSONObject

        try {
            jsonObject = JSONObject(responseBody)
            val jsonobject1 = jsonObject.getJSONObject("culturalEventInfo")
            val jsonArray=jsonobject1.getJSONArray("row")

            for(i in 0 until jsonArray.length()){
                val item = jsonArray.getJSONObject(i)
                title = item.getString("TITLE")
                date = item.getString("DATE")
                image = item.getString("MAIN_IMG")
                // 이미지가 다른 사이트로 연결되어 있으면, http://culture.seoul.go.kr 을 지워줌
                if(image.contains("www"))
                    image = image.replace("http://culture.seoul.go.kr","")

                /** 타이틀에 html 인코딩이 잘 못 되어있을 경우 수정해주기*/
                if(title.contains('&')){
                    if(title.contains("&nbsp"))
                        title = title.replace("&nbsp;", " ")
                    if(title.contains("&lt"))
                        title = title.replace("&lt;", "<")
                    if(title.contains("&gt"))
                        title = title.replace("&gt;", ">")
                    if(title.contains("&amp"))
                        title = title.replace("&amp;", "and")
                    if(title.contains("&apos"))
                        title = title.replace("&apos;", "'")
                    if(title.contains("&quot"))
                        title = title.replace("&quot;", "")
                }

                println("title : $title")
                println("date: $date")
                println("image : $image")

                var contentItem = ContentItem(image,title,date)
                contentItems.add(contentItem)
            }
        } catch (e: JSONException){
            e.printStackTrace()
        }
    }
}

/**
방법1
$ curl -X POST -H "Authorization: Bearer "$(gcloud auth application-default print-access-token) -H "Content-Type: application/json; charset=utf-8" -d '{"payload": {"row": {"values": ["무용", "강남구", "여", "유료", "온가족"]}}}' https://automl.googleapis.com/v1beta1/projects/saiki-200813/locations/us-central1/models/TBL4476826519234150400:predict
## 설명
$(gcloud auth application-default print-access-token) 토큰값을 요청('/root/ce.json'설정이 되야함)

 * */