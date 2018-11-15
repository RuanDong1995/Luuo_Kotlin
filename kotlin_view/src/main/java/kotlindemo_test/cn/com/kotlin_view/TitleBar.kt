package kotlindemo_test.cn.com.kotlin_view

import android.content.ContentValues.TAG
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.StateListDrawable
import android.text.TextUtils
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.RelativeLayout.*
import android.widget.TextView

/**
 * @packagename kotlindemo_test.cn.com.kotlin_view
 * @details
 * @date 2018/11/5
 * @author ruandong
 */
open class TitleBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr)
{


    /**
     * 按钮字体大小，单位为dp
     */
    private val BUTTON_TEXT_SIZE = 13
    /**
     * 标题字体大小，单位为dp
     */
    private val TITLE_TEXT_SIZE = 17

    /**
     * 默认背景色
     */
    private val COLOR_DEFAULT = 0x00000000
    /**
     * 按下状态背景色
     */
    private val COLOR_PRESSED = 0x20000000

    /**
     * 按钮宽度，单位为dp
     */
    private val BUTTON_MIN_WIDTH = 50
    /**
     * 按钮边距，单位为dp
     */
    private val BUTTON_PADDING: Float = 10F


    var mLeftButton : FrameLayout
    var mRightButton = FrameLayout(context)
    var mCenterTitle = FrameLayout(context)

    private var mLeftTextColor: Int = 0
    private var mRightTextColor: Int = 0
    private var mTitleTextColor: Int = 0



    init {
        val buttonPadding = dip2px(context, BUTTON_PADDING)
        //左边按钮
        mLeftButton = FrameLayout(context)
        mLeftButton.id = R.id.title_bar_left_button
        mLeftButton.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding)
        mLeftButton.minimumHeight = BUTTON_MIN_WIDTH
        val leftParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        leftParams.addRule(ALIGN_PARENT_LEFT)
        leftParams.addRule(CENTER_VERTICAL)
        mLeftButton.layoutParams = leftParams
        addView(mLeftButton)

        //右边按钮
        mRightButton.id = R.id.title_bar_right_button
        mRightButton.setPadding(buttonPadding, buttonPadding, buttonPadding, buttonPadding)
        mRightButton.minimumHeight = BUTTON_MIN_WIDTH
        val rightParams = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        rightParams.addRule(ALIGN_PARENT_RIGHT)
        rightParams.addRule(CENTER_VERTICAL)
        mRightButton.layoutParams = rightParams
        addView(mRightButton)

        val titleParams =
            LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        titleParams.addRule(RIGHT_OF, mLeftButton.id)
        titleParams.addRule(LEFT_OF, mRightButton.id)
        titleParams.addRule(CENTER_IN_PARENT)
        mCenterTitle.layoutParams = titleParams
        addView(mCenterTitle)

        parseAttr(context, attrs)
    }

    private fun parseAttr(context: Context?, attrs: AttributeSet?) {

        if (attrs == null) {
            return
        }
        val typedArray = context!!.obtainStyledAttributes(attrs, R.styleable.TitleBar)
        val leftTxt = typedArray.getString(R.styleable.TitleBar_leftText)
        mLeftTextColor = typedArray.getColor(R.styleable.TitleBar_leftTextColor, -0x1000000)
        val leftDrawable = typedArray.getDrawable(R.styleable.TitleBar_leftButton)
        val rightTxt = typedArray.getString(R.styleable.TitleBar_rightText)
        mRightTextColor = typedArray.getColor(R.styleable.TitleBar_rightTextColor, -0x1000000)
        val rightDrawable = typedArray.getDrawable(R.styleable.TitleBar_rightButton)
        val titleText = typedArray.getString(R.styleable.TitleBar_titleText)
        mTitleTextColor = typedArray.getColor(R.styleable.TitleBar_titleTextColor, -0x1000000)
        val leftButtonPadding =
            typedArray.getDimensionPixelSize(R.styleable.TitleBar_leftButtonPadding, BUTTON_PADDING.toInt())

        typedArray.recycle()

        // 实例化左边按钮
        if (leftDrawable != null) {
            val imageView = ImageView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            imageView.layoutParams = layoutParams
            imageView.setImageDrawable(leftDrawable)
            mLeftButton.addView(imageView)
        } else if (!TextUtils.isEmpty(leftTxt)) {
            val textView = TextView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.text = leftTxt
            textView.layoutParams = layoutParams
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, BUTTON_TEXT_SIZE.toFloat())
            textView.setTextColor(mLeftTextColor)
            mLeftButton.addView(textView)
        }
        mLeftButton.setPadding(leftButtonPadding, leftButtonPadding, leftButtonPadding, leftButtonPadding)

        // 实例化右边按钮
        if (rightDrawable != null) {
            val imageView = ImageView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            imageView.layoutParams = layoutParams
            imageView.setImageDrawable(rightDrawable)
            mRightButton.addView(imageView)
        } else if (!TextUtils.isEmpty(rightTxt)) {
            val textView = TextView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.layoutParams = layoutParams
            textView.text = rightTxt
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, BUTTON_TEXT_SIZE.toFloat())
            textView.setTextColor(mRightTextColor)
            mRightButton.addView(textView)
        }

        // 实例化标题
        if (!TextUtils.isEmpty(titleText)) {
            val textView = TextView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.layoutParams = layoutParams
            textView.text = titleText
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TITLE_TEXT_SIZE.toFloat())
            textView.setTextColor(mTitleTextColor)
            mCenterTitle.addView(textView)
        }

    }

    open fun addTitleView(layoutId: Int) {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(layoutId, mCenterTitle, false)
        addTitleView(view)
    }

    open fun addTitleView(view: View) {
        if (mCenterTitle.childCount > 0) mCenterTitle.removeAllViews()
        val layoutParams =
            FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        layoutParams.gravity = Gravity.CENTER
        view.layoutParams = layoutParams
        mCenterTitle.addView(view)

    }


    /**
     * 设置标题
     *
     * @param title
     */
    open fun setTitle(title: String) {
        if (mCenterTitle.childCount > 0) {
            val view = mCenterTitle.getChildAt(0)
            if (view is TextView) {
                view.text = title
            }
        } else {
            val textView = TextView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.layoutParams = layoutParams
            textView.text = title
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, TITLE_TEXT_SIZE.toFloat())
            textView.setSingleLine()
            textView.ellipsize = TextUtils.TruncateAt.END
            textView.setTextColor(mTitleTextColor)
            mCenterTitle.addView(textView)
        }
    }

    /**
     * 设置标题文字颜色
     *
     * @param color 颜色
     */
    open fun setTitleTextColor(color: Int) {
        mTitleTextColor = color
        if (mCenterTitle.childCount > 0) {
            val view = mCenterTitle.getChildAt(0)
            if (view is TextView) {
                view.setTextColor(color)
            }
        }
    }

    /**
     * 设置左边按钮文字
     */
    open fun setLeftButtonText(text: String) {
        if (mLeftButton.childCount > 0) {
            val view = mLeftButton.getChildAt(0)
            if (view is TextView) {
                view.text = text
                return
            }
        } else {
            val textView = TextView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.text = text
            textView.layoutParams = layoutParams
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, BUTTON_TEXT_SIZE.toFloat())
            textView.setTextColor(mLeftTextColor)
            mLeftButton.addView(textView)
        }
    }

    /**
     * 设置左边按钮文字颜色
     *
     * @param color 颜色
     */
    open fun setLeftButtonTextColor(color: Int) {
        mLeftTextColor = color
        if (mLeftButton.childCount > 0) {
            val view = mLeftButton.getChildAt(0)
            if (view is TextView) {
                view.setTextColor(color)
                return
            }
        }
    }

    /**
     * 设置右边按钮文字
     */
    open fun setRightButtonText(text: String) {
        if (mRightButton.childCount > 0) {
            val view = mRightButton.getChildAt(0)
            if (view is TextView) {
                view.text = text
                return
            }
        } else {
            val textView = TextView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT
            )
            layoutParams.gravity = Gravity.CENTER
            textView.text = text
            textView.layoutParams = layoutParams
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, BUTTON_TEXT_SIZE.toFloat())
            textView.setTextColor(mRightTextColor)
            mRightButton.addView(textView)
        }
    }

    /**
     * 设置右边按钮文字颜色
     *
     * @param color 颜色
     */
    open fun setRightButtonTextColor(color: Int) {
        mRightTextColor = color
        if (mRightButton.childCount > 0) {
            val view = mRightButton.getChildAt(0)
            if (view is TextView) {
                view.setTextColor(color)
                return
            }
        }
    }

    /**
     * 设置左按钮图片资源id
     *
     * @param resId 图片资源id
     */
    open fun setLeftButton(resId: Int) {
        var imageView: ImageView? = null
        if (mLeftButton.childCount > 0) {
            val view = mLeftButton.getChildAt(0)
            if (view is ImageView) {
                imageView = view
            } else {
                mLeftButton.removeAllViews()
            }
        }
        if (imageView == null) {
            imageView = ImageView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            imageView.layoutParams = layoutParams
            mLeftButton.addView(imageView)
        }
        imageView.setImageResource(resId)
    }

    /**
     * 设置右按钮图片资源id
     *
     * @param resId 图片资源id
     */
    open fun setRightButton(resId: Int) {
        var imageView: ImageView? = null
        if (mRightButton.childCount > 0) {
            val view = mRightButton.getChildAt(0)
            if (view is ImageView) {
                imageView = view
            } else {
                mRightButton.removeAllViews()
            }
        }
        if (imageView == null) {
            imageView = ImageView(context)
            val layoutParams = FrameLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.gravity = Gravity.CENTER
            imageView.layoutParams = layoutParams
            mRightButton.addView(imageView)
        }
        imageView.setImageResource(resId)
    }

    /**
     * 清除左侧按钮
     */
    open fun clearLeftButton() {
        mLeftButton.removeAllViews()
        mLeftButton.setOnClickListener(null)
    }

    /**
     * 清除右侧按钮
     */
    open fun clearRightButton() {
        mRightButton.removeAllViews()
        mRightButton.setOnClickListener(null)
    }


    /**
     * 获取左边按钮
     *
     * @return
     */
    open fun getLeftButton(): FrameLayout {
        return mLeftButton
    }

    /**
     * 获取右边按钮
     *
     * @return
     */
    open fun getRightButton(): FrameLayout {
        return mRightButton
    }

    /**
     * 获取标题栏layout
     *
     * @return
     */
    open fun getTitleLayout(): FrameLayout {
        return mCenterTitle
    }

    /**
     * 创建点击效果背景
     *
     * @return
     */
    private fun createClickDrawable(): StateListDrawable {
        val drawable = StateListDrawable()
        val pressedDrawable = ColorDrawable(COLOR_PRESSED)
        val defaultDrawable = ColorDrawable(COLOR_DEFAULT)
        drawable.addState(intArrayOf(android.R.attr.state_pressed, android.R.attr.state_enabled), pressedDrawable)
        drawable.addState(intArrayOf(), defaultDrawable)
        return drawable
    }


    private fun dip2px(context: Context?, dip: Float): Int {
        val scale = context!!.resources.displayMetrics.density
        return (scale * dip + 0.5f).toInt()
    }
}