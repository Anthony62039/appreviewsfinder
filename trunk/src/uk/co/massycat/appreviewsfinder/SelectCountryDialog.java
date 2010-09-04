//
// Copyright (C) 2009 Ben Jaques.
// All rights reserved.
//
// Redistribution and use in source and binary forms, with or without
// modification, are permitted provided that the following conditions are met:
//
// - Redistributions of source code must retain the above copyright notice, this
//   list of conditions and the following disclaimer.
//
// - Redistributions in binary form must reproduce the above copyright notice,
//   this list of conditions and the following disclaimer in the documentation
//   and/or other materials provided with the distribution.
//
// - Neither the name of the author nor the names of its contributors may be used
//   to endorse or promote products derived from this software without specific
//   prior written permission.
//
// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
// AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
// IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
// DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
// FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
// DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
// SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
// CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
// OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
// OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
//

/*
 * SelectCountryDialog.java
 *
 * Created on Aug 28, 2009, 6:07:19 PM
 */
package uk.co.massycat.appreviewsfinder;

import uk.co.massycat.appreviewsfinder.countries.CountryEntry;
import java.awt.Component;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

/**
 *
 * @author ben
 */
public class SelectCountryDialog extends javax.swing.JDialog {
    private String mSelectedCode = null;

    class TempCountry implements Comparable<TempCountry> {

        public String mCode;
        public CountryEntry mEntry;

        @Override
        public String toString() {
            return mCode + ", " + mEntry.mName;
        }

        public int compareTo(TempCountry o) {
            return mEntry.compareTo(o.mEntry);
        }
    }

    class TempRenderer implements ListCellRenderer {

        public Component getListCellRendererComponent(JList list,
                Object value,
                int index,
                boolean isSelected,
                boolean cellHasFocus) {
            TempCountry country = (TempCountry) value;
            JLabel label = new JLabel(country.toString(), country.mEntry.mFlag, SwingConstants.LEFT);

            return label;
        }

    }

    /** Creates new form SelectCountryDialog */
    public SelectCountryDialog(java.awt.Frame parent, boolean modal, String current_code,
            Map<String,CountryEntry> code_country_map) {
        super(parent, modal);
        initComponents();

        DefaultComboBoxModel combo_model = ((DefaultComboBoxModel) mCountryComboBox.getModel());
        combo_model.removeAllElements();

        //CountriesManager manager = CountriesManager.getManager();
        //Set<String> code_set = manager.getAllCountryCodes();
        Set<String> code_set = code_country_map.keySet();
        Iterator<String> iterator = code_set.iterator();
        //LinkedList country_list = new LinkedList();
        int current_index = 0;

        for (String code = iterator.next(); iterator.hasNext(); code = iterator.next(), current_index++) {
            TempCountry temp = new TempCountry();

            temp.mCode = code;
            //temp.mEntry = manager.getCountryEntry(code);
            temp.mEntry = code_country_map.get(code);
            //System.out.println(temp.mEntry);

            //country_list.add(temp);
            combo_model.addElement(temp);

            if ( code.equals(current_code)) {
                mCountryComboBox.setSelectedIndex(current_index);
            }
        }

//        Collections.sort(country_list);
//        //System.out.print(country_list);
//        int list_size = country_list.size();
//
//        for ( int i = 0; i < list_size; i++) {
//            TempCountry temp = (TempCountry)country_list.remove();
//
//            combo_model.addElement(temp);
//        }

        mCountryComboBox.setRenderer(new TempRenderer());
    }

    public String getSelectedCode() {
        return mSelectedCode;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mCountryComboBox = new javax.swing.JComboBox();
        jPanel1 = new javax.swing.JPanel();
        mCancelButton = new javax.swing.JButton();
        mOkButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Set iTunes Store location");

        mCountryComboBox.setMinimumSize(new java.awt.Dimension(47, 40));
        mCountryComboBox.setPreferredSize(new java.awt.Dimension(200, 40));
        mCountryComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCountryComboBoxActionPerformed(evt);
            }
        });
        getContentPane().add(mCountryComboBox, java.awt.BorderLayout.CENTER);

        mCancelButton.setText("Cancel");
        mCancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mCancelButtonActionPerformed(evt);
            }
        });
        jPanel1.add(mCancelButton);

        mOkButton.setText("Ok");
        mOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mOkButtonActionPerformed(evt);
            }
        });
        jPanel1.add(mOkButton);

        getContentPane().add(jPanel1, java.awt.BorderLayout.SOUTH);

        getAccessibleContext().setAccessibleName("Choose iTunes Store location");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mCountryComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCountryComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mCountryComboBoxActionPerformed

    private void mCancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mCancelButtonActionPerformed
        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_mCancelButtonActionPerformed

    private void mOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mOkButtonActionPerformed
        mSelectedCode = ((TempCountry)mCountryComboBox.getSelectedItem()).mCode;

        this.setVisible(false);
        this.dispose();
    }//GEN-LAST:event_mOkButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton mCancelButton;
    private javax.swing.JComboBox mCountryComboBox;
    private javax.swing.JButton mOkButton;
    // End of variables declaration//GEN-END:variables
}