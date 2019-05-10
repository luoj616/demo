package demo.luojun.com.demo.fragment.testfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import demo.luojun.com.demo.R;
import demo.luojun.com.demo.fragment.BaseFragment;

/**

 */
public class CMyOrderListPagerFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
 private Activity context;





    // TODO: Rename and change types and number of parameters
    public static CMyOrderListPagerFragment newInstance(String param1, String param2) {
        CMyOrderListPagerFragment fragment = new CMyOrderListPagerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//       FragmentManager fragmentManager = getChildFragmentManager();
//        fragmentManager.beginTransaction();
//        Fragment fragment =getParentFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment1, container, false);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = (Activity) context;
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


}
