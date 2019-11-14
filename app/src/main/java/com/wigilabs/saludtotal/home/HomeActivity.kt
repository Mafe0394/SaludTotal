package com.wigilabs.saludtotal.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.navigation.NavigationView
import com.wigilabs.saludtotal.R
import com.wigilabs.saludtotal.affiliationstatus.AffiliationStatusFragment
import com.wigilabs.saludtotal.base.BaseActivity
import com.wigilabs.saludtotal.databinding.ActivityHomeBinding
import com.wigilabs.saludtotal.dialogs.AlertDialogGeneral
import com.wigilabs.saludtotal.dialogs.AlertDialogModel
import com.wigilabs.saludtotal.dialogs.showInfoDialog
import com.wigilabs.saludtotal.fragments.HomeFragment
import com.wigilabs.saludtotal.fragments.apps.AppsFragment
import com.wigilabs.saludtotal.fragments.directory.DirectoryFragment
import com.wigilabs.saludtotal.fragments.historiaClinica.HistoriaClinicaFragment
import com.wigilabs.saludtotal.fragments.news.NewsFragment
import com.wigilabs.saludtotal.fragments.notifications.NotificationFragment
import com.wigilabs.saludtotal.fragments.yourData.YourDataFragment
import com.wigilabs.saludtotal.historiaClinica.HistoriaClinicaActivity
import com.wigilabs.saludtotal.start.StartActivity
import kotlinx.android.synthetic.main.app_bar_home_menu.view.*

class HomeActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    lateinit var binding:ActivityHomeBinding
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this, R.layout.activity_home)

        //Seteamos el toobar
        setSupportActionBar(binding.toolbarHomeMenu1.toolbar_home_menu)

        //Icono Hamburguesa para desplegar el Navigation View
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_st_general_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        //Listener para manejar los fragments
        binding.navView.setNavigationItemSelectedListener(this)
        supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,HomeFragment()).
            commit()

    }



    override fun onSupportNavigateUp(): Boolean {
        binding.homeDrawerLayout.openDrawer(GravityCompat.START)
        return true
    }

    override fun onNavigationItemSelected(p0: MenuItem): Boolean {
        when(p0.itemId){
            R.id.nav_home->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,HomeFragment()).
                    commit()
                setNombreActionBar(R.string.nombre_muestra,R.id.nav_home)
            }
            R.id.nav_your_data->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,YourDataFragment()).
                commit()
            }
            R.id.nav_notifications->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,NotificationFragment()).
                commit()
            }
            R.id.nav_news->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,NewsFragment()).
                    commit()
            }
            R.id.nav_directory->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,DirectoryFragment()).
                    commit()
            }
            R.id.nav_related_apps->{
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,AppsFragment()).
                    commit()
            }
            R.id.nav_close_sesion->{

                with(AlertDialogGeneral()){
                    onClickAceptar={startActivity(Intent(this@HomeActivity,StartActivity::class.java))
                        finish()}
                    show(this@HomeActivity,
                    AlertDialogModel("¿Estás seguro de cerrar sesión?","",R.string.ok,
                        R.string.cancelar))
                }
            }
        }
        //Reemplazo de fragment

        p0.isChecked = true
        binding.homeDrawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    fun onClick(v: View){
        when(v.id){
            R.id.home_btn_citas_medicas->{
                Toast.makeText(v.context,"1",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_citas_medicas,v.id)
            }
            R.id.home_btn_autorizaciones->{
                Toast.makeText(v.context,"2",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_autorizaciones,v.id)
            }
            R.id.home_btn_radicaciones->{
                Toast.makeText(v.context,"3",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_radicaciones,v.id)
            }
            R.id.home_btn_certificaciones->{
                Toast.makeText(v.context,"4",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_certificaciones,v.id)
            }
            R.id.home_btn_estado_afiliacion->{
                Toast.makeText(v.context,"5",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,
                    AffiliationStatusFragment()
                ).
                    commit()
                setNombreActionBar(R.string.home_lb_estado_afiliacion,v.id)
            }
            R.id.home_btn_laboratorios->{
                Toast.makeText(v.context,"6",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_laboratorios,v.id)
            }
            R.id.home_btn_cambio_ips->{
                Toast.makeText(v.context,"7",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_cambio_ips,v.id)
            }
            R.id.home_btn_historia_clinica->{
                Toast.makeText(v.context,"8",Toast.LENGTH_SHORT).show()
                supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment,HistoriaClinicaFragment()).
                    commit()
                setNombreActionBar(R.string.home_lb_historia_clinica,v.id)
            }
            R.id.home_btn_chateaconnosotros->{
                Toast.makeText(v.context,"9",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_chateaconnosotros,v.id)
            }
            R.id.home_btn_orientacion_medica->{
                Toast.makeText(v.context,"10",Toast.LENGTH_SHORT).show()
                setNombreActionBar(R.string.home_lb_orientacion_medica,v.id)
            }
            R.id.btn_hc_consultar->{
                Toast.makeText(v.context,"consultar",Toast.LENGTH_SHORT).show()
                startActivity(Intent(v.context,HistoriaClinicaActivity::class.java))
            }
        }
    }
    fun setNombreActionBar(StringId:Int,ResId:Int){
        if(ResId!=R.id.nav_home){
            with(binding.toolbarHomeMenu1.tv_title_ab_home_menu){
                    text =resources.getString(StringId)
                }
        }else{
            binding.toolbarHomeMenu1.ly_home_action_bar.gravity=Gravity.LEFT
            with(binding.toolbarHomeMenu1.tv_title_ab_home_menu){
                text =resources.getString(R.string.home_tb_welcome)
        } }
    }
}
