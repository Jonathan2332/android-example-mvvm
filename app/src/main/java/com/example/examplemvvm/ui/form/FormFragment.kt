package com.example.examplemvvm.ui.form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.examplemvvm.R
import com.example.examplemvvm.constants.EGuestStatus
import com.example.examplemvvm.databinding.FormFragmentBinding
import com.example.examplemvvm.model.Guest
import com.example.examplemvvm.ui.home.HomeFragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class FormFragment : Fragment(), View.OnClickListener, KoinComponent {

    companion object
    {
        fun newInstance() = FormFragment()
    }

    private lateinit var binding: FormFragmentBinding
    private val viewModel: FormViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FormFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSave.setOnClickListener(this)

        viewModel.viewState.observe(viewLifecycleOwner) { viewState ->

            when(viewState)
            {
                FormViewState.SaveGuestError ->
                {
                    binding.progressBar.isVisible = false
                    binding.buttonSave.isEnabled = true
                    Toast.makeText(requireContext(), "Error on save guest", Toast.LENGTH_LONG).show()
                }
                FormViewState.SavedGuest ->
                {
                    binding.progressBar.isVisible = false
                    binding.buttonSave.isEnabled = true
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.container, HomeFragment.newInstance())
                        .commitNow()

                    viewModel.resetViewState()

                }
                FormViewState.SavingGuest -> {
                    binding.progressBar.isVisible = true
                    binding.buttonSave.isEnabled = false
                }
            }
        }
    }

    override fun onClick(v: View?)
    {
        if(v != null)
        {
            if(v.id == binding.buttonSave.id)
            {
                val status = when(binding.radioConfirmation.checkedRadioButtonId) {
                    binding.radioPresent.id -> EGuestStatus.PRESENT
                    binding.radioAbsent.id -> EGuestStatus.ABSENT
                    else -> EGuestStatus.NOT_CONFIRMED
                }
                viewModel.saveGuest(Guest(name = binding.editGuestName.text.toString(), presence = status))
            }
        }
    }
}