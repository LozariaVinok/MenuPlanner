package irina.menuplanner.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import irina.menuplanner.model.entities.ShoppingListItem

@Dao
interface ShoppingListDao {

    @Query("SELECT item.shoppingListItemId, product.productId, product.name, item.isChecked FROM ShoppingListItem item, Product product WHERE item.productItemId = product.productId")
    fun getShoppingList(): LiveData<List<ShoppingListProductItem>>

    @Insert
    suspend fun insertItem(item: ShoppingListItem): Long

    @Query("UPDATE ShoppingListItem SET isChecked = :checked WHERE shoppingListItemId = :id")
    suspend fun updateItem(id: Long, checked: Boolean)

    @Query("DELETE FROM ShoppingListItem WHERE shoppingListItemId = :id")
    suspend fun deleteItem(id: Long)

    @Query("SELECT * FROM ShoppingListItem WHERE shoppingListItemId = :id")
    fun getItem(id: Long): ShoppingListItem

}