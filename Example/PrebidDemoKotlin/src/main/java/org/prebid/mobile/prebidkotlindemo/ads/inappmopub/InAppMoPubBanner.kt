package org.prebid.mobile.prebidkotlindemo.ads.inappmopub

import android.annotation.SuppressLint
import android.view.ViewGroup
import com.mopub.common.MoPub
import com.mopub.common.SdkConfiguration
import com.mopub.mobileads.MoPubView
import org.prebid.mobile.rendering.bidding.data.AdSize
import org.prebid.mobile.rendering.bidding.display.MoPubBannerAdUnit

object InAppMoPubBanner {

    @SuppressLint("StaticFieldLeak")
    private var bannerView: MoPubView? = null
    private var adUnit: MoPubBannerAdUnit? = null

    fun create(
        wrapper: ViewGroup,
        autoRefreshTime: Int,
        width: Int,
        height: Int,
        adUnitId: String,
        configId: String
    ) {
        adUnit = MoPubBannerAdUnit(wrapper.context, configId, AdSize(width, height))
        adUnit?.setRefreshInterval(autoRefreshTime)
        bannerView = MoPubView(wrapper.context)
        bannerView?.setAdUnitId(adUnitId)

        wrapper.addView(bannerView)

        MoPub.initializeSdk(wrapper.context, SdkConfiguration.Builder(adUnitId).build()) {
            adUnit?.fetchDemand(bannerView) {
                bannerView?.loadAd()
            }
        }
    }

    fun destroy() {
        bannerView?.destroy()
        bannerView = null

        adUnit?.stopRefresh()
        adUnit = null
    }

}