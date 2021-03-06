/*
 * Copyright (C) 2016 Nishant Srivastava
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github.nisrulz.projectrecyclerviewhelper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import github.nisrulz.recyclerviewhelper.RVHAdapter;
import github.nisrulz.recyclerviewhelper.RVHViewHolder;
import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder>
    implements RVHAdapter {

  private final List<String> dataList;

  public MyAdapter(List<String> dataList) {
    this.dataList = dataList;
  }

  @Override
  public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

    View view =
        LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_list_item, parent, false);

    return new ItemViewHolder(view);
  }

  @Override
  public void onBindViewHolder(ItemViewHolder holder, int position) {
    holder.txt.setText(dataList.get(position));
  }

  @Override
  public int getItemCount() {
    return dataList.size();
  }

  @Override
  public boolean onItemMove(int fromPosition, int toPosition) {
    swap(fromPosition, toPosition);
    return false;
  }

  @Override
  public void onItemDismiss(int position, int direction) {
    remove(position);
  }

  private void remove(int position) {
    dataList.remove(position);
    notifyItemRemoved(position);
  }

  private void swap(int firstPosition, int secondPosition) {
    Collections.swap(dataList, firstPosition, secondPosition);
    notifyItemMoved(firstPosition, secondPosition);
  }

  public class ItemViewHolder extends RecyclerView.ViewHolder implements RVHViewHolder {
    final TextView txt;

    public ItemViewHolder(View itemView) {
      super(itemView);
      txt = itemView.findViewById(R.id.txt);
    }

    @Override
    public void onItemSelected(int actionstate) {
      System.out.println("Item is selected");
    }

    @Override
    public void onItemClear() {
      System.out.println("Item is unselected");
    }
  }
}
