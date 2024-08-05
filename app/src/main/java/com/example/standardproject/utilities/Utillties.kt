package com.example.standardproject.utilities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.util.DisplayMetrics
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.AbsListView
import android.widget.BaseAdapter
import android.widget.FrameLayout
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.standardproject.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import java.io.File
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


val shouldNavigateToLogin = MutableLiveData(false)

object Extensions {
//    fun getUserData(context: Context): UserModel.Data? {
//        val userModel = SharedPreferencesManager.getInstance(context).userData
//        val userData = Gson().fromJson(userModel, UserModel::class.java)
//        return userData.data
//    }


    fun Fragment.handleOnBackPressed(action: () -> Unit) {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    action()
                }
            }
        )
    }

//    fun provideLoadingDialog(context: Context): androidx.appcompat.app.AlertDialog? {
//        val builder = MaterialAlertDialogBuilder(context)
//        val inflater = LayoutInflater.from(context)
//        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
//        builder.setCancelable(false)
//
//        val dialog = builder.create()
//
//        val window = dialog.window
//
//        window?.setLayout(
//            WindowManager.LayoutParams.WRAP_CONTENT,
//            WindowManager.LayoutParams.WRAP_CONTENT
//        )
//
//        return dialog
//    }

//    fun showFile(file: String, context: Context) {
//        val fileExtension = File(file).extension.toLowerCase(Locale.ENGLISH)
//        debug(fileExtension)
//        if (fileExtension == "png" || fileExtension == "jpeg" || fileExtension == "jpg") {
//            showImageDialog(file, context)
//        } else {
//            goBrowser(file, context)
//        }
//    }
//
//    private fun goBrowser(link: String, context: Context) {
//        val myIntent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
//        context.startActivity(myIntent)
//    }

//    private fun showImageDialog(filePath: String, context: Context) {
//        val dialog = Dialog(context, android.R.style.Theme_NoTitleBar_Fullscreen)
//        dialog.setContentView(R.layout.dialog_full_image)
//        val imageView = dialog.findViewById<ImageView>(R.id.fullscreen_image)
//        val close = dialog.findViewById<ImageView>(R.id.close_dialog)
//
//        close.setOnClickListener {
//            dialog.dismiss()
//        }
//
//        Picasso.get()
//            .load(filePath)
//            .placeholder(R.drawable.work_flow)
//            .error(R.drawable.work_flow)
//            .into(imageView)
//
//        dialog.show()
//    }

    fun ImageView.setBackButtonAction(activity: Activity) {
        this.setOnClickListener {
            activity.onBackPressed()
        }
    }

//    fun showConfirmationDialog(
//        context: Context,
//        navController: NavController? = null,
//        dialogLayoutRes: Int,
//        buttonText: String,
//        navigateAction: NavDirections,
//        dialogBackgroundRes: Int
//    ) {
//        val builder = MaterialAlertDialogBuilder(context)
//        val inflater = LayoutInflater.from(context)
//        val dialogView = inflater.inflate(dialogLayoutRes, null)
//        builder.setView(dialogView)
//        builder.setCancelable(false)
//        val dialog = builder.create()
//
//        // Get a reference to the main button and change its text
//        val mainButton = dialogView.findViewById<TextView>(R.id.tv_button)
//        val goToLogin = dialogView.findViewById<ConstraintLayout>(R.id.bt_next)
//        goToLogin.setOnClickListener {
//            dialog.dismiss()
//            navController!!.navigate(navigateAction)
//        }
//        mainButton.text = buttonText
//        dialog.window?.setBackgroundDrawableResource(dialogBackgroundRes)
//        dialog.show()
//    }
//
//    fun View.showSnackBar(message: String, duration: Int = Snackbar.LENGTH_LONG) {
//        val snackbar = Snackbar.make(this, message, duration)
//        val snackbarView = snackbar.view
//        val params = snackbarView.layoutParams as FrameLayout.LayoutParams
//        params.setMargins(
//            params.leftMargin,
//            params.topMargin,
//            params.rightMargin,
//            params.bottomMargin + 50
//        )
//        snackbarView.layoutParams = params
//        snackbar.show()
//    }

//     fun getDrawableForFileExtension(ext: String): Int {
//        return when (ext.lowercase()) {
//            "ppt", "pptx" -> R.drawable.ppt_placeholder
//            "doc", "docx" -> R.drawable.doc_placeholder
//            "pdf" -> R.drawable.pdf_placeholder
//            "xls", "xlsx" -> R.drawable.xls_placeholder
//            "txt" -> R.drawable.txt_placeholder
//            else -> R.drawable.default_placeholder
//        }
//    }

//    @SuppressLint("RestrictedApi", "MissingInflatedId")
//    fun View.showCustomSnackBar(
//        title: String,
//        message: String,
//        icon: String,
//        duration: Int = Snackbar.LENGTH_LONG
//    ) {
//        val snackbar = Snackbar.make(this, "", duration)
//        val customView = LayoutInflater.from(context).inflate(R.layout.custom_notification, null)
//
//        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
//        snackbarLayout.setPadding(0, 100, 0, 0)
//        snackbarLayout.addView(customView, 0)
//
//        val titleTextView = customView.findViewById<TextView>(R.id.snackbar_title)
//        val messageTextView = customView.findViewById<TextView>(R.id.snackbar_message)
//        val image = customView.findViewById<ImageView>(R.id.snackbar_icon)
//
//        titleTextView.text = title
//        messageTextView.text = message
//        Picasso.get().load(icon).into(image)
//
//        snackbar.view.setBackgroundColor(Color.TRANSPARENT)
//
//        val params = snackbar.view.layoutParams as FrameLayout.LayoutParams
//        params.gravity = Gravity.TOP
//        snackbar.view.layoutParams = params
//
//        snackbar.show()
//    }

//    fun showAlertDialog(
//        context: Context,
//        message: String,
//        yes: String?,
//        no: String?,
//        onYesClicked: () -> Unit
//    ) {
//        val messageText = SpannableString(message)
//        messageText.setSpan(ForegroundColorSpan(Color.BLACK), 0, messageText.length, 0)
//        messageText.setSpan(StyleSpan(Typeface.NORMAL), 0, messageText.length, 0)
//
//        val textView = TextView(context)
//        textView.text = messageText
//        textView.textSize = 16f
//        val padding = 24 * context.resources.displayMetrics.density.toInt()
//        textView.setPadding(padding, padding, padding, padding)
//
//        val dialog = MaterialAlertDialogBuilder(context)
//            .setView(textView)
//            .setNegativeButton(no ?: "No") { dialog, which -> dialog.dismiss() }
//            .setPositiveButton(yes ?: "Yes") { dialog, which -> onYesClicked() }
//            .create()
//
//        dialog.setOnShowListener {
//            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
//            positiveButton.setTextColor(Color.BLACK)
//            positiveButton.setTypeface(positiveButton.typeface, Typeface.BOLD)
//
//            val negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
//            negativeButton.setTextColor(Color.BLACK)
//            negativeButton.setTypeface(negativeButton.typeface, Typeface.BOLD)
//        }
//
//        dialog.window?.setBackgroundDrawableResource(R.drawable.alert_background)
//        dialog.show()
//
//        val window = dialog.window
//        val displayMetrics = context.resources.displayMetrics
//        val width = (displayMetrics.widthPixels * 0.80).toInt()
//        window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
//    }


//    fun showGroupDeletedDialog(
//        context: Context,
//        title: String,
//        description: String,
//        onOkClicked: () -> Unit
//    ) {
//        val messageText = SpannableString(description)
//        messageText.setSpan(ForegroundColorSpan(Color.BLACK), 0, messageText.length, 0)
//        messageText.setSpan(StyleSpan(Typeface.NORMAL), 0, messageText.length, 0)
//
//        val textView = TextView(context)
//        textView.text = messageText
//        textView.textSize = 16f
//        val padding = 24 * context.resources.displayMetrics.density.toInt()
//        textView.setPadding(padding, padding, padding, padding)
//
//        val dialog = MaterialAlertDialogBuilder(context)
//            .setTitle(title)
//            .setView(textView)
//            .setCancelable(false)
//            .setPositiveButton("OK") { dialog, _ ->
//                dialog.dismiss()
//                onOkClicked()
//            }
//            .create()
//
//        dialog.setOnShowListener {
//            val positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
//            positiveButton.setTextColor(Color.BLACK)
//            positiveButton.setTypeface(positiveButton.typeface, Typeface.BOLD)
//        }
//
//        dialog.window?.setBackgroundDrawableResource(R.drawable.alert_background)
//        dialog.show()
//
//        val window = dialog.window
//        val displayMetrics = context.resources.displayMetrics
//        val width = (displayMetrics.widthPixels * 0.80).toInt()
//        window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
//    }


//    fun setPriorityColor(context: Context, priority: String, textView: TextView) {
//        textView.text = priority
//
//        when (priority) {
//            "High" -> {
//                debug("High")
//                textView.setTextColor(context.getColor(R.color.high))
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.highBackground))
//            }
//
//            "Medium" -> {
//                debug("Medium")
//                textView.setTextColor(context.getColor(R.color.medium))
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.mediumBackground))
//            }
//
//            "Low" -> {
//                debug("Low")
//                textView.setTextColor(context.getColor(R.color.low))
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.lowBackground))
//            }
//
//            "Urgent" -> {
//                debug("Urgent")
//                textView.setTextColor(context.getColor(R.color.urgent))
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.urgentBackground))
//            }
//
//            else -> {
//                textView.setTextColor(context.getColor(R.color.rejected))
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.rejectedBackground))
//            }
//        }
//    }
//
//
//    fun getTypeColor(context: Context, emailType: String, textView: TextView) {
//        textView.text = emailType
//
//        when (emailType) {
//            "ticket" -> {
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.ticket))
//            }
//
//            "task" -> {
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.task))
//            }
//
//            else -> textView.backgroundTintList =
//                ColorStateList.valueOf(context.getColor(R.color.ticket))
//        }
//    }

    fun debug(message: String) {
        Log.e("WORKFLOW", message)
    }

//    fun getTicketTypeColor(context: Context, ticketType: String, textView: TextView) {
//        textView.text = ticketType
//        when (ticketType) {
//            "internal", "external" -> textView.backgroundTintList =
//                ColorStateList.valueOf(context.getColor(R.color.statusBackground2))
//
//            else -> textView.backgroundTintList =
//                ColorStateList.valueOf(context.getColor(R.color.statusBackground2))
//        }
//    }
//
//    fun getStatusColor(context: Context, statusType: String, textView: TextView) {
//        textView.text = statusType
//        when (statusType) {
//            "Pending" -> {
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.pendingBackground))
//                textView.setTextColor(context.getColor(R.color.pending))
//
//            }
//
//            "Closed" -> {
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.closedBackground))
//                textView.setTextColor(context.getColor(R.color.closed))
//            }
//
//            "Rejected" -> {
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.rejectedBackground))
//                textView.setTextColor(context.getColor(R.color.rejected))
//            }
//
//            else -> {
//                textView.backgroundTintList =
//                    ColorStateList.valueOf(context.getColor(R.color.createdBackground))
//                textView.setTextColor(context.getColor(R.color.created))
//            }
//        }
//    }

    fun RecyclerView.onEndOfScroll(loadMore: () -> Unit) {
        var lastItemLoaded = false

        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    lastItemLoaded = false
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()
                val visibleItemCount = layoutManager.childCount
                val totalItemCount = layoutManager.itemCount

                val lastItem = firstVisibleItem + visibleItemCount
                if (lastItem == totalItemCount && !lastItemLoaded) {
                    lastItemLoaded = true
                    loadMore()
                }
            }
        })
    }

    fun RecyclerView.onStartOfScroll(loadMore: () -> Unit) {
        var firstItemLoaded = false

        this.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    firstItemLoaded = false
                }
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val firstVisibleItem = layoutManager.findFirstVisibleItemPosition()

                if (firstVisibleItem == 0 && !firstItemLoaded) {
                    firstItemLoaded = true
                    loadMore()
                }
            }
        })
    }


//    fun Fragment.handleBackPressWithNavigation() {
//        // Get the NavController
//        val navController = findNavController()
//
//        // Handle the back press
//        requireActivity().onBackPressedDispatcher.addCallback(
//            viewLifecycleOwner,
//            object : OnBackPressedCallback(true) {
//                override fun handleOnBackPressed() {
//                    when (this@handleBackPressWithNavigation) {
//
//
//                        is DashboardFragment, is HubFragment, is ProfileFragment, is ChatGroupsFragment -> {
//                            showAlertDialog(
//                                requireContext(),
//                                "Are you sure you want to quit?",
//                                null,
//                                null
//                            ) {
//                                requireActivity().finish()
//                            }
//                        }
//
//                        is LoginFragment -> {
//                            showAlertDialog(
//                                requireContext(),
//                                "Are you sure you want to quit?",
//                                null,
//                                null
//                            ) {
//                                requireActivity().finish()
//                            }
//                        }
//
//                        else -> {
//                            isEnabled = false
//                            requireActivity().onBackPressed()
//                        }
//                    }
//                }
//            })
//    }

    fun String.formatDate(
        inputFormat: String = "yyyy-MM-dd HH:mm:ss",
        outputFormat: String = "dd MMM yyyy"
    ): String {
        return try {
            val parser = SimpleDateFormat(inputFormat, Locale.getDefault())
            val date: Date = parser.parse(this) ?: return "Invalid Date"

            val formatter = SimpleDateFormat(outputFormat, Locale.getDefault())
            formatter.format(date)
        } catch (e: Exception) {
            "Invalid Date"
        }
    }

    fun setTypefaceForTextInputLayout(textInputLayout: TextInputLayout) {
        val typeface = Typeface.create("default", Typeface.BOLD)
        textInputLayout.typeface = typeface
    }

//
//    fun backToPreviousFragment(fragment: Fragment) {
//        findNavController(fragment).popBackStack()
//    }


    fun GridView.setupScrolling(loadMore: () -> Unit) {
        var lastItemLoaded = false
        var isFirstLoad = true

        this.setOnScrollListener(object : AbsListView.OnScrollListener {
            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {

                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
                    lastItemLoaded = false
                    (this@setupScrolling.adapter as BaseAdapter).notifyDataSetChanged()

                }
            }

            override fun onScroll(
                view: AbsListView?,
                firstVisibleItem: Int,
                visibleItemCount: Int,
                totalItemCount: Int
            ) {
                if (isFirstLoad) {
                    isFirstLoad = false
                    return
                }

                val lastItem = firstVisibleItem + visibleItemCount
                if (lastItem == totalItemCount && !lastItemLoaded) {
                    lastItemLoaded = true

                    loadMore()
                }
            }
        })


//        this.setOnScrollListener(object : AbsListView.OnScrollListener {
//            override fun onScrollStateChanged(view: AbsListView?, scrollState: Int) {
//                // Update the list when the user scrolls
//                (this@setupScrolling.adapter as BaseAdapter).notifyDataSetChanged()
//            }
//
//            override fun onScroll(
//                view: AbsListView?,
//                firstVisibleItem: Int,
//                visibleItemCount: Int,
//                totalItemCount: Int
//            ) {
//                // Update the list when the user scrolls
//                (this@setupScrolling.adapter as BaseAdapter).notifyDataSetChanged()
//            }
//        })
    }


    fun Fragment.showDatePicker(textView: TextView) {
        val today = Calendar.getInstance()
        val ninetyDaysFromToday = Calendar.getInstance().apply {
            add(Calendar.DAY_OF_YEAR, 90)
        }

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, monthOfYear, dayOfMonth)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
                val formattedDate = dateFormat.format(selectedDate.time)
                textView.text = formattedDate
            },
            today.get(Calendar.YEAR),
            today.get(Calendar.MONTH),
            today.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.minDate = today.timeInMillis
        datePickerDialog.datePicker.maxDate = ninetyDaysFromToday.timeInMillis
        datePickerDialog.show()
    }

//    fun Fragment.showDatePicker(textView: TextView) {
//        val today = MaterialDatePicker.todayInUtcMilliseconds()
//        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
//        calendar.timeInMillis = today
//        calendar.add(Calendar.DAY_OF_YEAR, 30)
//        val ninetyDaysFromToday = calendar.timeInMillis
//
//        val constraintsBuilder = CalendarConstraints.Builder()
//            .setStart(today)
//            .setEnd(ninetyDaysFromToday)
//            .setValidator(DateValidatorPointForward.now())
//
//        val datePicker = MaterialDatePicker.Builder.datePicker()
//            .setTitleText("Select a date")
//            .setCalendarConstraints(constraintsBuilder.build())
//            .build()
//
//        datePicker.show(requireActivity().supportFragmentManager, datePicker.toString())
//
//        datePicker.addOnPositiveButtonClickListener { selection ->
//            val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(selection))
//            textView.text = selectedDate
//        }
//    }


    fun Double.format(): String {
        val df = DecimalFormat("#0.00", DecimalFormatSymbols(Locale.ENGLISH))
        return df.format(this)
    }
//
//    fun hideBottomNavBar(navController: NavController, view: View) {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//                R.id.dashboardFragment, R.id.hubFragment, R.id.profileFragment, R.id.chatListFragment -> {
//                    view.visibility = View.VISIBLE
//                }
//
//                else -> {
//                    view.visibility = View.GONE
//                }
//            }
//        }
//    }
//
//
//    fun updateStatusBarColor(activity: Activity, navController: NavController) {
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            when (destination.id) {
//
//                R.id.loginFragment -> {
//                    activity.window.statusBarColor =
//                        ContextCompat.getColor(activity, R.color.loginBackground)
//                    WindowCompat.getInsetsController(activity.window, activity.window.decorView)
//                        .isAppearanceLightStatusBars = true
//
//                }
//
//                else -> {
//                    activity.window.statusBarColor = ContextCompat.getColor(
//                        activity,
//                        android.R.color.transparent
//                    )
//                    WindowCompat.getInsetsController(activity.window, activity.window.decorView)
//                        .isAppearanceLightStatusBars = true
//                }
//            }
//        }
//    }
}

data class UserLoginData(
    val email: String,
    val password: String,
)

