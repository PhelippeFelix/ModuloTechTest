package felix.phelippe.modulotechtest.models.utils

import android.view.View
import android.view.View.OnLongClickListener
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener


class ItemClickSupport private constructor(
    private val mRecyclerView: RecyclerView,
    private val mItemID: Int
) {
}
