package br.com.wellingtoncosta.githubusers.ui.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * @author Wellington Costa on 26/12/2017.
 */
public class BaseViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final T binding;

    public BaseViewHolder(View view) {
        super(view);
        this.binding = DataBindingUtil.bind(view);
    }

    public T getBinding() {
        return binding;
    }

}