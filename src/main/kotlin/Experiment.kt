import org.json.JSONArray
import org.json.JSONObject

fun main(args: Array<String>) {
    println("test")

    val example = "[\"{\\\"msg\\\":\\\"sub\\\",\\\"id\\\":\\\"ozvtuHcfZM5v2iQdF\\\",\\\"name\\\":\\\"paginatedFiles\\\",\\\"params\\\":[{\\\"skip\\\":0,\\\"limit\\\":10,\\\"sortBy\\\":{\\\"updatedAt\\\":-1},\\\"filterBy\\\":{\\\"assigneeId\\\":\\\"uLGMsJg0GCS0aF6BN\\\"}}]}\"]"
    val jsonArray = JSONArray(example)
    val jsonArray2 = JSONArray()
    val prettifiedJsonArray = ArrayList<String>()
    for (i in 0 until jsonArray.length()) {
        val jsonObject = JSONObject(jsonArray.getString(i))

        // Prettify the JSON object
        val prettyJson = jsonObject.toString(4)
        jsonArray2.put(jsonObject)
        // Add the prettified JSON to the new array
        //prettifiedJsonArray.add(prettyJson)
    }

    // Print the prettified JSON array
    //println(prettifiedJsonArray)
    println(jsonArray2.toString(4))
    //Json()
}